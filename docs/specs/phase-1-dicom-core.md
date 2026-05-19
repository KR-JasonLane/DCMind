# Phase 1 스펙 — DICOM 코어 + 수신

> 상태: 초안 (사용자 리뷰 대기)
> 선행: 없음 (1~2단계는 인프라 없이 진행 가능. Spring/DB가 필요해지는 시점에 Phase 0 기반 작업을 삽입한다)
> 관련: `docs/overview/00-overview.md`

---

## 1. 목표

DICOM 파일이 HTTP로 들어와서, 파싱·저장·인덱싱되고, 다시 조회되는 **PACS의 척추**를 완성한다.

**완료 데모 기준** (UI 없음 — curl/HTTP 클라이언트 기준):
1. 비압축 DICOM 파일을 STOW-RS로 POST하면 → 파싱되어 원본이 스토리지에, 메타데이터가 DB에 저장된다.
2. QIDO-RS로 "환자 ID가 X인 스터디 목록"을 JSON으로 조회할 수 있다.
3. WADO-RS로 특정 인스턴스의 메타데이터와 픽셀 데이터(원본)를 받을 수 있다.
4. 위 전 과정이 자동화된 테스트로 검증된다 (TDD).

## 2. 범위

### 포함
- dcm4che 기반 파싱 계층 (래핑, 도메인 모델로 변환)
- Patient / Study / Series / Instance 4계층 메타데이터 인덱스 (Postgres)
- 원본 DICOM 파일 스토리지 (로컬 파일시스템, 경로 전략 포함)
- STOW-RS 수신, QIDO-RS 조회, WADO-RS 검색 (각각 표준의 실용적 부분집합)
- 수신 검증: 필수 태그 존재, 지원 전송구문 확인, 중복 처리

### 제외 (다른 Phase / 미래)
- 압축 전송구문 (JPEG*, RLE) — 미지원 시 명확한 에러로 거절
- 인증·권한 (Phase 0에서 최소 구성, 본 Phase API에는 일단 미적용)
- 뷰어 (Phase 2), 워크리스트·FHIR (Phase 3), 에이전트 (Phase 4)
- DIMSE (C-STORE/C-FIND), 오브젝트 스토리지(S3 등), 삭제·수정 API

## 3. 도메인 모델

DICOM의 정보 계층을 그대로 반영한다. 각 계층의 키는 DICOM UID.

```
Patient (PatientID, PatientName, BirthDate, Sex)
  └─ Study (StudyInstanceUID†, StudyDate/Time, AccessionNumber, StudyDescription)
       └─ Series (SeriesInstanceUID†, Modality, SeriesNumber, SeriesDescription)
            └─ Instance (SOPInstanceUID†, SOPClassUID, InstanceNumber,
                         Rows, Columns, BitsAllocated, PhotometricInterpretation,
                         TransferSyntaxUID, 원본 파일 경로, 파일 크기, SHA-256 해시)
```
† = 전역 유일 키 (DB unique 제약)

- 파싱 결과는 우리 도메인 객체로 변환하며, **dcm4che 타입(`Attributes` 등)은 `dicom` 패키지 밖으로 노출하지 않는다.**
- Patient는 PatientID 기준 upsert (같은 환자의 두 번째 스터디는 기존 Patient에 연결).

## 4. 저장 설계

- **메타데이터 인덱스**: Postgres. 위 4계층을 각각 테이블로, 부모 FK 연결.
- **원본 파일**: 로컬 파일시스템. 경로 전략: `{storage-root}/{StudyInstanceUID}/{SeriesInstanceUID}/{SOPInstanceUID}.dcm`
- 저장은 트랜잭션 순서로: 파일 먼저 쓰고 → DB 인덱스 커밋. DB 실패 시 파일 롤백(삭제).
- **해시 기록**: 저장 시 SHA-256을 계산해 파일 크기와 함께 인덱스에 기록한다. 용도 — ① 사후 무결성 검증(디스크 손상·변조 탐지)의 기준값, ② 재수신 동일성 판별, ③ 백업·이관 검증. (검증 잡·통계 API 등 운영 기능 자체는 Phase 3 이후)
- 동일 SOPInstanceUID 재수신 시: 해시 비교로 판별 — **해시 동일이면 멱등 성공**(저장 생략, 표준 STOW 관례), **해시 상이면 충돌로 거절**(같은 UID에 다른 내용 = 이상 상황).

## 5. API 설계 (DICOMweb 부분집합)

기본 경로: `/dicomweb`

### 5.1 STOW-RS — 수신
- `POST /dicomweb/studies`
- 요청: `multipart/related; type="application/dicom"` (파트당 DICOM 파일 1개, 다중 파트 지원)
- 응답: 202 스타일 대신 표준을 단순화 — 성공 200 + 저장 결과 JSON(성공/실패 인스턴스 목록), 전체 실패 시 400/415.
- 거절 사유: DICM 매직 없음, 필수 태그 누락(SOPInstanceUID 등), 미지원 전송구문.

### 5.2 QIDO-RS — 조회
- `GET /dicomweb/studies?PatientID=...&StudyDate=...` — 스터디 검색
- `GET /dicomweb/studies/{studyUID}/series` — 스터디 내 시리즈
- `GET /dicomweb/studies/{studyUID}/series/{seriesUID}/instances` — 시리즈 내 인스턴스
- 응답: **DICOM JSON 모델(PS3.18)** 형식 — `{"00100020": {"vr": "LO", "Value": ["12345"]}}` 꼴. 고정된 반환 속성 집합(위 도메인 모델의 필드들)으로 시작.
- 페이징: `limit`/`offset` 파라미터.

### 5.3 WADO-RS — 검색
- `GET /dicomweb/studies/{studyUID}/series/{seriesUID}/instances/{instanceUID}` — 원본 DICOM 파일 반환 (`application/dicom`)
- `GET .../instances/{instanceUID}/metadata` — 메타데이터 JSON
- (프레임 단위 픽셀 추출 `/frames/{n}`은 Phase 2에서 뷰어 요구에 맞춰 추가)

## 6. 에러 처리 원칙

| 상황 | 동작 |
|---|---|
| DICOM 아님 (DICM 매직 없음) | 해당 파트 실패 기록, 400 계열 |
| 미지원 전송구문 (압축) | 거절 + 응답에 사유 명시 (전송구문 UID 포함) |
| 필수 태그 누락 | 거절 + 누락 태그 명시 |
| 동일 SOPInstanceUID 재수신 (해시 동일) | 멱등 성공 (저장 생략) |
| 동일 SOPInstanceUID 재수신 (해시 상이) | 충돌 거절 (409) + 사유 명시 |
| 존재하지 않는 UID 조회 | 404 |
| 파일 저장 성공 후 DB 실패 | 파일 삭제 롤백, 5xx |

## 7. 테스트 전략 (TDD)

- 모든 기능은 **테스트 먼저** (Red → Green → Refactor). CLAUDE.md 규칙 5 적용.
- **유닛**: 파싱 계층 — 샘플 DICOM(`src/test/resources`)을 읽어 도메인 객체 필드 검증. 깨진 파일·태그 누락 케이스 포함.
- **통합**: STOW→QIDO→WADO 왕복 — Spring 컨텍스트 + 실제 Postgres(Testcontainers)로 "업로드한 것이 조회된다"를 검증.
- 샘플 데이터: 공개 도메인 비압축 DICOM 수 개 + dcm4che로 합성 생성한 최소 DICOM(경계 케이스용).

## 8. 학습 단위 (요약 — 상세 절차는 `docs/plans/phase-1.md`에서)

| # | 단위 | 핵심 학습 개념 | 체크포인트 |
|---|---|---|---|
| 1-1 | DICOM 데이터 모델 개념 학습 (코드 없음) | 파일 구조, 태그/VR, UID 계층, 전송구문 | 구조를 그림으로 설명 가능 |
| 1-2 | 콘솔로 dcm4che 파싱 맛보기 | Gradle, dcm4che `DicomInputStream`/`Attributes` | 샘플 파일의 환자·스터디 태그 출력 |
| 1-3 | 도메인 모델 + 파싱 래퍼 (TDD 시작) | Java record/클래스 설계, JUnit, 래핑 경계 | 파싱 유닛테스트 통과 |
| — | **(Phase 0 삽입)** Spring Boot 스캐폴드 + Postgres + Docker Compose | Spring 구조, JPA, 컨테이너 | 앱 기동 + DB 연결 |
| 1-4 | 저장 계층: 스키마 + 파일 스토리지 | JPA 엔티티/관계, 트랜잭션, upsert | 파싱 결과가 DB+파일로 저장 |
| 1-5 | STOW-RS 엔드포인트 | multipart/related, Spring MVC, 에러 응답 설계 | curl 업로드 → 저장 (통합테스트) |
| 1-6 | QIDO-RS 조회 | 동적 쿼리, DICOM JSON 직렬화, 페이징 | 검색 JSON 응답 (통합테스트) |
| 1-7 | WADO-RS 검색 | 파일 스트리밍 응답, 콘텐츠 협상 | 원본/메타데이터 반환 (통합테스트) |

각 단위는 CLAUDE.md의 학습 루프(테스트 제시 → 구현 제시 → 설명 → 질의 → 사용자가 직접 심고 확인)로 진행한다.

## 9. 열린 결정 (Phase 진행 중 확정)

- Java 버전(권장: LTS 21) 및 Spring Boot 버전 — Phase 0 삽입 시점에 확정
- 샘플 DICOM 출처 선정
- QIDO 반환 속성 집합의 정확한 목록 — 1-6 시작 시 확정

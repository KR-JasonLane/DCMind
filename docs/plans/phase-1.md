# Phase 1 구현 계획 — DICOM 코어 + 수신

> 스펙: `docs/specs/phase-1-dicom-core.md`
> 진행 방식: 각 단위는 CLAUDE.md의 **기능 단위 학습 루프**로 진행한다 —
> Claude가 ① 테스트 코드 제시 → ② 구현 코드 제시 → ③ 설명(새 문법·엔진 특성 포함) → ④ 사용자 질의·이해 → ⑤ 사용자가 직접 심고 확인(Red→Green).

---

## 단위 1-1 — DICOM 데이터 모델 개념 학습 (코드 없음)

- **무엇을**: DICOM 파일 구조와 정보 모델을 이해한다.
- **왜**: 라이브러리(dcm4che)를 쓰더라도, 태그·VR·UID 계층을 모르면 파싱 결과를 해석할 수 없다. Phase 1 전체의 어휘를 여기서 만든다.
- **학습 개념**: ① 파일 구조(preamble, DICM, File Meta, 데이터 엘리먼트) ② 태그(group,element)와 VR ③ Transfer Syntax(암시적/명시적 VR, 리틀 엔디안) ④ 정보 계층(Patient→Study→Series→Instance)과 3대 UID ⑤ DICOMweb 3형제(STOW/QIDO/WADO)의 역할.
- **진행**: Claude가 자료를 단계별로 설명 → 사용자 질의응답.
- **체크포인트**: 사용자가 "DICOM 파일을 열면 무엇이 어떤 순서로 나오는지"와 "UID 3개가 각각 무엇을 식별하는지"를 자기 말로 설명할 수 있다.

## 단위 1-2 — Gradle 콘솔 프로젝트 + dcm4che 파싱 맛보기

- **무엇을**: `backend/`에 Gradle 프로젝트를 만들고, 순수 `main()` 콘솔에서 샘플 DICOM 파일을 dcm4che로 열어 주요 태그를 출력한다. (Spring 없음)
- **왜**: 인프라 없이 Java·Gradle·dcm4che에 먼저 손을 데운다. "파싱이 된다"는 즉각적 성취가 이후 단위의 동력이 된다.
- **학습 개념**: Gradle 프로젝트 구조(build.gradle, 의존성 선언), dcm4che 핵심 API(`DicomInputStream`, `Attributes`, `Tag` 상수), Java try-with-resources.
- **진행 순서**: ① 샘플 비압축 DICOM 확보(공개 데이터, Claude가 출처 안내) ② Gradle 초기화 ③ 파싱 콘솔 코드(학습 루프) ④ 실행.
- **체크포인트**: 콘솔에 PatientID·PatientName·StudyInstanceUID·Modality·Rows/Columns·TransferSyntaxUID가 출력된다.
- *참고: 이 단위는 "맛보기"이므로 TDD 예외 — 다음 단위부터 테스트 먼저.*

## 단위 1-3 — 도메인 모델 + 파싱 래퍼 (TDD 시작)

- **무엇을**: 스펙 §3의 도메인 객체(Patient/Study/Series/Instance 메타)를 정의하고, "DICOM 스트림 → 도메인 객체" 변환기(파싱 래퍼)를 TDD로 만든다. dcm4che 타입은 이 패키지 밖으로 내보내지 않는다.
- **왜**: 시스템 전체가 딛고 설 핵심 추상화. 래핑 경계(라이브러리 격리)라는 설계 원칙을 여기서 체득한다.
- **학습 개념**: JUnit 5(테스트 구조, assertion), Java record, 패키지 경계 설계, 테스트 리소스(`src/test/resources`) 로딩, 예외 설계(필수 태그 누락·비DICOM·미지원 전송구문).
- **진행 순서**: ① 정상 파일 파싱 테스트(Red) → 구현(Green) ② 필수 태그 누락 케이스 ③ 비DICOM 파일 케이스 ④ 미지원 전송구문 케이스.
- **체크포인트**: `./gradlew test` 그린. 깨진 입력 3종이 의도한 예외로 거절된다.

## 단위 P0 (Phase 0 삽입) — Spring Boot + Postgres + Docker Compose

- **무엇을**: 콘솔 프로젝트를 Spring Boot 앱으로 승격하고, Docker Compose로 Postgres를 띄워 연결한다.
- **왜**: 1-4부터는 DB와 HTTP가 필요하다. 학습 관성이 붙은 지금이 인프라를 얹을 적기.
- **학습 개념**: Spring Boot 구조(@SpringBootApplication, 자동 설정, application.yml), 의존성 주입(DI)과 Bean, Docker Compose 기초, Spring 프로파일.
- **진행 순서**: ① Spring Boot 의존성 전환(보일러플레이트 성격 — 요청 시 Claude가 생성 가능) ② docker-compose.yml(Postgres) ③ DB 연결 확인.
- **체크포인트**: `docker compose up` 후 앱이 기동되고 Postgres 연결 로그가 정상.
- *Phase 0의 나머지(JWT 인증, CI)는 Phase 1 완료 후 별도 진행.*

## 단위 1-4 — 저장 계층: 스키마 + 파일 스토리지 + 해시

- **무엇을**: 4계층 JPA 엔티티·리포지토리, 원본 파일 저장(경로 전략), SHA-256 계산·기록, "파일 먼저→DB 커밋→실패 시 파일 롤백" 저장 서비스를 TDD로.
- **왜**: PACS의 본체. 트랜잭션 경계·멱등성·무결성이라는 백엔드 핵심 주제가 전부 등장한다.
- **학습 개념**: JPA 엔티티/연관관계(@ManyToOne 등)·unique 제약, @Transactional 동작 원리, upsert 패턴, MessageDigest(SHA-256), 통합 테스트와 Testcontainers.
- **진행 순서**: ① 엔티티+리포지토리 테스트 ② 파일 스토리지 서비스 ③ 저장 오케스트레이션(해시·멱등·충돌·롤백 케이스별 테스트 먼저).
- **체크포인트**: 파싱→저장 통합 테스트 그린 — 정상 저장 / 동일 해시 재수신 멱등 / 상이 해시 409 / DB 실패 시 파일 롤백.

## 단위 1-5 — STOW-RS 수신 엔드포인트

- **무엇을**: `POST /dicomweb/studies` — multipart/related 파싱, 파트별 수신→검증→저장, 성공/실패 목록 응답.
- **왜**: 첫 HTTP 관문. 스펙 §5.1·§6의 에러 정책이 실제 API 응답으로 구현된다.
- **학습 개념**: Spring MVC(@RestController, 요청 매핑), multipart/related 처리, HTTP 상태 코드 설계, MockMvc/통합 테스트로 API 검증.
- **진행 순서**: ① 단일 파트 성공 케이스(테스트 먼저) ② 다중 파트 ③ 거절 케이스들(비DICOM·태그 누락·미지원 전송구문·해시 충돌).
- **체크포인트**: curl로 실제 파일 업로드 → 200 + 결과 JSON, DB·스토리지에 반영 확인.

## 단위 1-6 — QIDO-RS 조회 API

- **무엇을**: 스터디 검색(`GET /dicomweb/studies?PatientID=...`)과 하위 시리즈/인스턴스 목록, DICOM JSON(PS3.18) 직렬화, 페이징.
- **왜**: "저장한 것을 찾을 수 있어야 PACS다". Phase 2 뷰어와 Phase 4 에이전트가 이 API를 소비한다.
- **학습 개념**: JPA 동적 조건 쿼리, DICOM JSON 모델 구조, DTO 매핑, 반환 속성 집합 확정(스펙 §9).
- **진행 순서**: ① 스터디 검색(테스트 먼저) ② 시리즈/인스턴스 목록 ③ 페이징·빈 결과·404.
- **체크포인트**: 업로드한 스터디가 PatientID 검색으로 DICOM JSON 형식으로 조회된다.

## 단위 1-7 — WADO-RS 검색 API

- **무엇을**: 인스턴스 원본 반환(`application/dicom`)과 메타데이터 JSON 반환.
- **왜**: 조회의 마지막 조각이자 Phase 2 뷰어의 데이터 공급선.
- **학습 개념**: 파일 스트리밍 응답(Resource), Content-Type 협상, 대용량 응답 처리.
- **진행 순서**: ① 메타데이터 엔드포인트 ② 원본 파일 반환 ③ 404·경로 무결성 케이스.
- **체크포인트**: **Phase 1 완료 데모** — STOW로 올린 파일이 QIDO로 검색되고 WADO로 바이트 동일하게(해시 일치) 회수된다. 전 과정 통합 테스트 그린.

---

## 완료 기준 (스펙 §1과 동일)

1. STOW-RS 업로드 → 파싱·저장 ✅  2. QIDO-RS 검색 ✅  3. WADO-RS 회수 ✅  4. 전 과정 자동 테스트 ✅

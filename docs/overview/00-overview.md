# DCMind — 프로젝트 개요

AI 에이전트를 갖춘 웹 PACS(Picture Archiving and Communication System).
포트폴리오 프로젝트이며, 아키텍처는 실사용 확장이 가능하도록 진지하게 설계한다. 최우선 목적은 **학습**.

> 진행 규칙은 루트의 `CLAUDE.md` 참고(로컬 전용, git 미추적). 이 문서와 `docs/`는 **프로젝트 내용**(개요·결정·로드맵·스펙)을 담는다.

---

## 확정된 기술 결정 (브레인스토밍, 2026-07-13)

| 항목 | 결정 |
|---|---|
| 목적 | 포트폴리오 (실사용 확장 열어둠), 학습 우선 |
| 백엔드 | **Java / Spring Boot** |
| DICOM 파싱 | **A-2** — 파싱은 **dcm4che** 라이브러리 사용, 그 위 계층(저장 구조·인덱싱·조회 API)을 직접 설계·구현 *(2026-07-14 변경: 직접 파서 A-1 → 라이브러리 A-2)* |
| DICOM 수신 | **B-1** — DICOMweb STOW-RS (HTTP) |
| 전송구문(Transfer Syntax) | **비압축만** 우선 (Implicit/Explicit VR Little Endian). 압축은 이후 Phase |
| 병원 플로우 | **EMR Mock + Modality Mock** 으로 전체 흐름 재현 |
| 시스템 포지션 | **RIS/PACS 통합형** — 독립형 RIS를 두지 않고, RIS의 워크플로우 기능을 DCMind가 흡수 (현대 통합형 제품 모델, 예: Fujifilm Synapse 계열) |
| EMR ↔ PACS 상호운용 | **HL7 FHIR** (ServiceRequest=오더 / DiagnosticReport=결과), 워크리스트는 UPS-RS 스타일 |
| AI | **대화형 LLM 에이전트 + tool calling** (권장: Claude). Vision AI(영상판독)는 미래 옵션 |
| 프론트엔드 | TypeScript (권장: React). LLM 프로바이더 최종 확정은 Phase 4에서 |

---

## 전체 시스템 흐름 (목표)

```
[EMR Mock] ──FHIR ServiceRequest(오더)──▶ [PACS] Worklist
                                             │
[Modality Mock] ──워크리스트 조회(UPS-RS풍)──┘
      │  DICOM 생성/로드 → STOW-RS(HTTP) 전송
      ▼
[PACS] ← dcm4che로 파싱 → 저장/인덱싱 (저장 구조·조회는 직접 설계)
      │
      ├──▶ [웹 뷰어]  판독의가 조회·측정
      ├──▶ [LLM 에이전트]  자연어로 조회·리포트 초안·워크플로우 조작
      │
      └──FHIR DiagnosticReport(결과)──▶ [EMR Mock]
```

---

## 로드맵 (Phase)

각 Phase는 독립적으로 데모 가능하며, 각자 스펙 → 구현 사이클을 가진다. Phase별 설계 스펙은 `docs/specs/`, 구현 계획은 `docs/plans/`에 둔다.

- **Phase 0 — 기반**: 프로젝트 스캐폴드, 저장소(메타데이터=Postgres, DICOM 원본=파일/오브젝트 스토리지), Docker Compose, 최소 인증(JWT·역할), `.gitignore` 교체, CI.
- **Phase 1 — DICOM 코어 + 수신 ⭐**: dcm4che 기반 파싱 계층, STOW-RS 수신, Patient/Study/Series/Instance 저장·인덱싱, QIDO-RS풍 조회 + WADO-RS풍 검색.
- **Phase 2 — 웹 뷰어**: 스터디 목록, 캔버스 DICOM 렌더링, window/level·확대·이동·기본 측정.
- **Phase 3 — 병원 플로우 / RIS 기능 계층 + Mock 액터**: DCMind가 흡수하는 RIS 워크플로우 기능 — 오더 접수(FHIR ServiceRequest→Accession Number 발급→워크리스트), UPS-RS풍 Modality Worklist, 검사 상태 추적(오더됨→촬영완료→판독대기→판독완료), 판독 리포트 작성·관리, 리포트 EMR 회신(FHIR DiagnosticReport). Mock 액터 — Modality Mock(워크리스트 조회→샘플 DICOM→STOW-RS), EMR Mock(오더 발행·리포트 수신).
  - *범위 제외 (RIS 기능 중 비채택)*: 검사실 예약·스케줄링 최적화, 수가 청구·수납, 부서 통계·행정.
  - *스토리지 운영 기능*: 용량·통계 조회 API, 무결성 검증 잡(저장 시 기록한 SHA-256 기준), 고아 데이터 탐지. (관리자 대시보드 UI는 옵션)
- **Phase 4 — LLM 에이전트**: PACS API를 도구로 호출(검색·열기·과거검사 요약·리포트 초안·EMR 회신), 뷰어에 챗 UI 통합.
- **Phase 5+ (미래)**: 압축 전송구문, DIMSE C-STORE, Vision AI 연동, 인증 강화, 스토리지 라이프사이클(보존 정책·계층화·백업).

---

## 리포지토리 폴더 구조 (제안 — Phase 진행에 따라 확장)

모노레포로 구성한다. 초기엔 `backend/`만 존재하고, Phase가 진행되며 `frontend/`, `mocks/` 등이 추가된다.

```
DCMind/
├─ CLAUDE.md                  # 작업 규칙 (Claude 전용, git-ignore — 로컬 전용)
├─ LICENSE
├─ .gitignore                 # Java/Gradle + Node/React + Claude 관련
├─ docker-compose.yml         # Postgres 등 인프라 (Phase 0~)
├─ README.md                  # 프로젝트 소개 (포트폴리오용)
│
├─ docs/                      # ── 프로젝트 문서 ──
│  ├─ overview/               # 개요·결정·로드맵·구조 (이 문서)
│  ├─ specs/                  # Phase별 설계 스펙 (예: phase-1-dicom-core.md)
│  └─ plans/                  # Phase별 구현 계획 (학습 단위 로드맵)
│
├─ backend/                   # ── PACS 코어 (Spring Boot) ──
│  ├─ build.gradle
│  ├─ settings.gradle
│  └─ src/
│     ├─ main/
│     │  ├─ java/com/dcmind/
│     │  │  ├─ dicom/         # DICOM 계층 (dcm4che 래핑)
│     │  │  │  ├─ parse/      # dcm4che로 파일 읽기·태그 추출·검증
│     │  │  │  └─ model/      # 파싱 결과 도메인 객체 (dcm4che 타입을 밖으로 노출하지 않음)
│     │  │  ├─ store/         # 저장·인덱싱 (Patient/Study/Series/Instance)
│     │  │  ├─ web/           # STOW-RS / QIDO-RS / WADO-RS 컨트롤러
│     │  │  ├─ fhir/          # FHIR 파사드 (Phase 3)
│     │  │  ├─ worklist/      # 워크리스트 (Phase 3)
│     │  │  ├─ agent/         # LLM 에이전트 오케스트레이션 (Phase 4)
│     │  │  └─ auth/          # 인증·권한
│     │  └─ resources/        # application.yml, DB 마이그레이션 등
│     └─ test/                # 유닛·통합 테스트 (test/resources에 샘플 DICOM 보관)
│
├─ frontend/                  # ── 웹 뷰어 + 에이전트 챗 (React + TS) ── (Phase 2~)
│  └─ src/
│     ├─ viewer/              # DICOM 캔버스 렌더링·측정
│     ├─ studylist/           # 스터디 목록 UI
│     └─ agent/               # 챗 UI (Phase 4)
│
└─ mocks/                     # ── Mock 액터 ── (Phase 3)
   ├─ modality-mock/          # 워크리스트 조회 → DICOM 생성 → STOW-RS 전송
   └─ emr-mock/               # FHIR 오더 발행 · 리포트 수신
```

> 참고: `mocks/`의 구현 언어(Java vs Node/TS)와 `frontend/`·프로젝트 통합 방식은 각 Phase 시작 시점에 확정한다.

---
id: dcmind
name: DCMind
category: healthcare-imaging
primary_color: "#00704A"
omd: "0.1"
tokens:
  source: prose-derived
  colors:
    primary: "#00704A"
    accent-green: "#00a862"
    deep-forest: "#1e3932"
    warm-white: "#f1f8f4"
    white: "#fafafa"
    cream: "#ede9e1"
    mist: "#d4e9e2"
    agent-gold: "#cba258"
    stat-berry: "#c44d58"
    success: "#00754a"
    error: "#e75b52"
    black: "#000000"
    charcoal: "#1e1e1e"
    body: "#4a4a4a"
    secondary: "#6b6b6b"
    disabled: "#8c8c8c"
    border: "#d4d4d4"
    light-gray: "#e8e8e8"
    bg-gray: "#f2f0eb"
  typography:
    family: { sans: "Pretendard", mono: "JetBrains Mono" }
    display-hero: { size: 48, weight: 700, lineHeight: 1.08, tracking: -0.5, use: "랜딩/로그인 타이틀" }
    display-lg:   { size: 36, weight: 700, lineHeight: 1.22, tracking: -0.25, use: "페이지 타이틀, 섹션 오프너" }
    heading-1:    { size: 28, weight: 700, lineHeight: 1.29, use: "주요 섹션 헤딩" }
    heading-2:    { size: 22, weight: 600, lineHeight: 1.36, use: "카드 헤딩, 서브 섹션" }
    heading-3:    { size: 18, weight: 600, lineHeight: 1.44, use: "리스트 헤더, 패널 타이틀" }
    subtitle:     { size: 16, weight: 600, lineHeight: 1.50, use: "내비게이션, 강조 라벨" }
    body-lg:      { size: 16, weight: 400, lineHeight: 1.63, use: "설명문" }
    body:         { size: 14, weight: 400, lineHeight: 1.57, use: "표준 본문, 테이블 셀" }
    caption:      { size: 12, weight: 400, lineHeight: 1.50, tracking: 0.2, use: "메타데이터, 태그값, 각주" }
    button:       { size: 14, weight: 600, lineHeight: 1.0, tracking: 0.3, use: "버튼 라벨" }
    mono-data:    { size: 13, weight: 400, lineHeight: 1.5, use: "DICOM UID·태그·Accession Number 등 식별자" }
  spacing: { xs: 4, sm: 8, md: 12, base: 16, lg: 24, xl: 32, xxl: 48, section: 96 }
  rounded: { sm: 4, md: 16, lg: 20, full: 9999 }
  shadow:
    subtle: "0 2px 8px rgba(30,57,50,0.08)"
    standard: "0 4px 16px rgba(30,57,50,0.12)"
    elevated: "0 6px 20px rgba(30,57,50,0.25)"
    modal: "0 12px 40px rgba(30,57,50,0.24)"
  components:
    button-primary: { type: button, bg: "#00704A", fg: "#ffffff", radius: 9999, padding: "12px 24px", font: "14px/600 Pretendard", use: "주요 액션 필 버튼, hover #1e3932" }
    button-secondary: { type: button, fg: "#00704A", radius: 9999, padding: "12px 24px", font: "14px/600 Pretendard", use: "아웃라인 그린 필, 1px #00704A border" }
    button-tertiary: { type: button, fg: "#00704A", padding: "8px 12px", font: "14px/600 Pretendard", use: "저강조 인라인 액션, hover 밑줄" }
    button-dark: { type: button, bg: "#fafafa", fg: "#00704A", radius: 9999, padding: "12px 24px", font: "14px/600 Pretendard", use: "다크 서피스(뷰어) 위 주요 액션" }
    button-agent: { type: button, bg: "#cba258", fg: "#1e3932", radius: 9999, padding: "12px 24px", font: "14px/700 Pretendard", use: "AI 에이전트 관련 CTA 전용" }
    input-text: { type: input, fg: "#1e1e1e", radius: 4, padding: "14px 16px", font: "16px/400 Pretendard", use: "표준 폼 입력, 1px #8c8c8c border, focus 2px #00704A" }
    input-search: { type: input, bg: "#f2f0eb", radius: 9999, padding: "12px 20px", font: "16px/400 Pretendard", use: "환자·스터디 검색 필" }
    card-study: { type: card, radius: 16, padding: "16px", use: "스터디/시리즈 카드, 1px #e8e8e8 border, 상단 썸네일" }
    card-editorial: { type: card, bg: "#f1f8f4", radius: 20, padding: "32px", use: "안내 블록, 온보딩, 빈 상태 패널" }
    card-agent: { type: card, fg: "#ffffff", radius: 16, padding: "24px", use: "AI 에이전트 패널 헤더, green→forest 그라데이션, 골드 아이콘" }
    badge-status: { type: badge, bg: "#00704A", fg: "#ffffff", radius: 9999, padding: "4px 12px", font: "11px/700 Pretendard", use: "판독완료 등 상태, uppercase" }
    badge-agent: { type: badge, bg: "#cba258", fg: "#1e3932", radius: 9999, padding: "4px 12px", font: "11px/700 Pretendard", use: "AI 제안·에이전트 산출물 표시" }
    badge-subtle: { type: badge, bg: "#d4e9e2", fg: "#00704A", radius: 9999, padding: "4px 12px", font: "11px/600 Pretendard", use: "Modality, 카테고리 라벨" }
    badge-stat: { type: badge, bg: "#c44d58", fg: "#ffffff", radius: 9999, padding: "4px 12px", font: "11px/700 Pretendard", use: "STAT/응급 검사 표시 전용" }
    toast-default: { type: toast, bg: "#1e3932", fg: "#ffffff", radius: 8, padding: "14px 18px", font: "14px/600 Pretendard", use: "일시적 확인 메시지" }
    toast-success: { type: toast, bg: "#d4e9e2", fg: "#00704A", radius: 8, padding: "12px 16px", use: "지속형 성공 배너, 4px #00704A 좌측 보더" }
    dialog-modal: { type: dialog, fg: "#1e1e1e", radius: 16, padding: "32px", use: "확인 대화상자, 그린 틴트 스크림" }
    toggle-switch: { type: toggle, bg: "#00704A", radius: 9999, use: "설정 스위치, #d4d4d4 off, #ffffff thumb" }
  components_harvested: true
---

# DCMind 디자인 가이드

> 이 가이드는 검증된 소비자 서비스 디자인 시스템의 구조를 바탕으로, DCMind(웹 PACS + AI 에이전트)에 맞게 정제한 것이다.
> 핵심 컨셉 — **하나의 그린, 따뜻한 오프화이트, 필(pill) 형태, 절제된 깊이, 넉넉한 여백** — 은 유지하되, 모든 역할 정의를 의료영상 워크플로우에 맞춘다.

## 1. 비주얼 테마와 분위기

DCMind의 인터페이스는 **차분한 신뢰**를 지향한다. 의료 소프트웨어는 흔히 차가운 파란색과 순백의 조합으로 "임상적"임을 표현하지만, DCMind는 반대로 간다: 깊고 확신 있는 **하우스 그린(`#00704A`)** 하나를 브랜드 축으로, 살짝 크림기가 도는 오프화이트(`#f1f8f4`, `#fafafa`) 서피스 위에 UI를 얹는다. 녹색은 의료에서 안정·회복의 색이면서, 수십 년 된 브랜드처럼 "같은 일을 오래 잘해온" 신뢰감을 준다.

인터페이스는 **두 개의 세계**로 나뉜다:
- **워크플로우 화면**(워크리스트, 검색, 관리, 리포트): 따뜻한 라이트 서피스. 텍스트는 웜 차콜, 여백은 넉넉하게.
- **뷰어 화면**(판독): **다크 서피스**. 판독은 어두운 환경에서 이루어지며 영상의 계조가 주인공이므로, 뷰어 크롬은 딥 포레스트(`#1e3932`)~차콜 계열로 가라앉히고 영상 캔버스는 순수 검정(`#000000`)을 쓴다. 다크 안에서도 그린 계열 틴트를 유지해 두 세계가 한 브랜드로 읽히게 한다.

타이포그래피는 **Pretendard**(휴머니스트 산스, 한글·라틴 혼용에 강함)를 워크호스로 쓰고, DICOM UID·태그·Accession Number 같은 **식별자는 모노스페이스**(JetBrains Mono)로 구분한다. 기하학적이고 차가운 서체는 피한다.

DCMind를 시각적으로 정의하는 것은 **절제된 따뜻함**이다: 단 하나의 지배적 그린, 웜 뉴트럴, 큰 라운드 형태(필 버튼), 그리고 영상이 주인공인 레이아웃. UI는 물러나고 영상과 데이터가 말하게 한다.

**핵심 특징:**
- 하우스 그린(`#00704A`)이 유일한 브랜드 색 — 주요 액션, 활성 상태, 브랜드 마크
- 웜 오프화이트 배경(`#f1f8f4`, `#fafafa`) — 순백의 차가움을 피함
- 뷰어는 딥 포레스트/차콜 다크 서피스 + 순흑 영상 캔버스
- 완전 라운드 **필 버튼**(border-radius 9999px)이 시그니처 형태
- 영상·데이터가 주인공인 레이아웃, UI 크롬은 최소화
- 넉넉한 여백과 큰 터치 타깃
- 골드(`#cba258`)는 **AI 에이전트 전용** 액센트, 베리(`#c44d58`)는 **STAT/응급 전용** 마커

## 2. 컬러 팔레트와 역할

### 기본색
- **House Green** (`#00704A`): 브랜드 그 자체. 주요 CTA, 링크, 활성 상태, 선택 표시.
- **Spring Green** (`#00a862`): 밝은 보조 그린. 하이라이트, 일러스트 액센트, 성공 인접 순간.
- **Deep Forest** (`#1e3932`): 그린 언더톤의 근흑색. 다크 서피스(뷰어 크롬, 푸터), 라이트 화면의 헤딩.
- **Warm White** (`#f1f8f4`): 옅은 그린 틴트의 오프화이트. 섹션 배경 기본값.
- **Pure-ish White** (`#fafafa`): 카드·페이지 배경.

### 보조색
- **Cream** (`#ede9e1`): 웜 뉴트럴 패널, 교차 섹션 배경.
- **Mist** (`#d4e9e2`): 그린 틴트 블록, 인포 배너, 서브틀 배지.
- **Agent Gold** (`#cba258`): **AI 에이전트 전용** — 에이전트 CTA, AI 산출물 배지, 에이전트 패널 액센트. 다른 용도 금지.
- **STAT Berry** (`#c44d58`): **응급/STAT 검사 전용** 마커. 아껴 쓸수록 강하다.

### 시맨틱 컬러
- **Success Green** (`#00754a`): 저장 완료, 검증 통과, 전송 성공.
- **Error Red** (`#e75b52`): 오류, 파괴적 액션, 폼 검증 실패.
- **Warning Amber** (`#cba258` 계열): 보류, 주의 필요. (에이전트 골드와 문맥으로 구분 — 배지 형태는 에이전트 전용)
- **Info** (`#1e3932` on `#d4e9e2`): 정보 배너는 외부 파랑 대신 그린 패밀리로.

### 뉴트럴 스케일
- **Black** (`#000000`): 영상 캔버스 배경 전용에 가깝게 절제.
- **Warm Charcoal** (`#1e1e1e`): 주요 헤딩 텍스트.
- **Body Gray** (`#4a4a4a`): 표준 본문.
- **Secondary Gray** (`#6b6b6b`): 캡션, 메타데이터.
- **Disabled Gray** (`#8c8c8c`): 플레이스홀더, 비활성 텍스트.
- **Border Gray** (`#d4d4d4`): 기본 보더, 디바이더, 입력 아웃라인.
- **Light Gray** (`#e8e8e8`): 미세 디바이더, 비활성 필.
- **Background Gray** (`#f2f0eb`): 웜 뉴트럴 섹션 필.

### 서피스와 보더
- **Border Default**: `#d4d4d4`.
- **Border Strong**: `#1e3932`.
- **Overlay Scrim**: `rgba(30,57,50,0.6)` — 그린 틴트 모달 배경(순흑 스크림 금지).
- **Viewer Chrome**: `#1e3932` 기반, 패널 구분은 `rgba(255,255,255,0.08)` 보더.

## 3. 타이포그래피 규칙

### 서체
- **Primary**: `"Pretendard", -apple-system, "Segoe UI", "Malgun Gothic", sans-serif` — 휴머니스트 산스, UI 워크호스.
- **Mono (식별자 전용)**: `"JetBrains Mono", "D2Coding", Consolas, monospace` — DICOM UID, 태그, Accession Number, 해시.
- 서체는 이 둘로 충분하다. 장식용 서드 폰트를 더하지 않는다.

### 위계

| 역할 | 크기 | 굵기 | 행간 | 자간 | 비고 |
|------|------|--------|-------------|----------------|-------|
| Display Hero | 48px | 700 | 1.08 | -0.5px | 랜딩/로그인 타이틀 |
| Display Large | 36px | 700 | 1.22 | -0.25px | 페이지 타이틀 |
| Heading 1 | 28px | 700 | 1.29 | normal | 주요 섹션 헤딩 |
| Heading 2 | 22px | 600 | 1.36 | normal | 카드 헤딩 |
| Heading 3 | 18px | 600 | 1.44 | normal | 리스트 헤더, 패널 타이틀 |
| Subtitle | 16px | 600 | 1.50 | normal | 내비게이션, 강조 라벨 |
| Body Large | 16px | 400 | 1.63 | normal | 설명문 |
| Body | 14px | 400 | 1.57 | normal | 표준 본문, 테이블 셀 |
| Caption | 12px | 400 | 1.50 | 0.2px | 메타데이터, 각주 |
| Button Label | 14–16px | 600 | 1.0 | 0.3px | |
| Mono Data | 13px | 400 | 1.5 | normal | UID·태그·해시 식별자 |

### 원칙
- **두 서체, 명확한 역할**: Pretendard가 UI 전부를, 모노는 식별자만. 한 컴포넌트에 섞어 쓰되 역할을 넘지 않는다.
- **휴머니스트 온기**: 차갑고 기계적인 지오메트릭 산스로 대체하지 않는다.
- **넉넉한 행간**: 본문 1.5–1.63. 조밀한 행간은 싸 보인다. (단, 데이터 테이블은 1.4까지 허용)
- **절제된 굵기**: 400 본문, 600 강조·버튼, 700 헤딩. 300·900은 쓰지 않는다.
- **문장 대소문자**: 헤드라인·본문은 문장형. 전대문자는 소형 배지·상태 라벨에만, 자간 추가와 함께.

## 4. 컴포넌트 스타일

### 버튼

시그니처는 **완전 라운드 필**(`border-radius: 9999px`)이다.

**Primary (Filled Green)** — bg `#00704a`, 텍스트 `#ffffff`, radius 9999px, padding 12px 24px, 14px/600, letter-spacing 0.3px. Hover `#1e3932`, Active `#16352e`, Disabled bg `#d4d4d4`/text `#8c8c8c`. 최소 높이 44px. 용도: "리포트 전송", "판독 시작", "저장".

**Secondary (Outlined Green)** — 투명 bg, 텍스트 `#00704a`, 1px `#00704a` 보더, 필. Hover bg `rgba(0,112,74,0.06)`. 용도: 주요 버튼 옆의 보조 액션 ("초기화", "내보내기").

**Tertiary / Text Link** — 텍스트 `#00704a`, hover 밑줄. 용도: 저강조 인라인 액션.

**Dark Surface (Filled White)** — bg `#fafafa`, 텍스트 `#00704a`, 필. 용도: 뷰어 등 다크 서피스 위의 주요 액션.

**Agent (Gold)** — bg `#cba258`, 텍스트 `#1e3932`, 필, 14px/700. 용도: **AI 에이전트 관련 CTA 전용** ("에이전트에게 묻기", "AI 초안 생성").

### 입력 필드

**Text Field** — 흰 bg, 1px `#8c8c8c` 보더, radius 4px, padding 14px 16px, 16px/400. 라벨은 상단 12px/600 `#4a4a4a`. Focus: 2px `#00704a` + `0 0 0 3px rgba(0,112,74,0.15)` 링.

**Text Field (Error)** — 2px `#e75b52` 보더, 아래 12px 헬퍼 텍스트 `#e75b52`. 구체적이고 실행 가능한 한 문장과 함께.

**Search Field** — bg `#f2f0eb`, 보더 없음, 필(9999px), padding 12px 20px, 선행 검색 아이콘 `#6b6b6b`. 용도: 환자·스터디 검색.

### 카드

**Study Card** — 흰 bg, 1px `#e8e8e8` 보더, radius 16px, 섀도 `0 2px 8px rgba(30,57,50,0.08)`. 상단에 시리즈 썸네일(다크), 본문 16px 패딩. 용도: 스터디/시리즈 타일.

**Editorial / Feature Card** — bg `#f1f8f4`, radius 20px, padding 32px, 섀도 없음(플랫 웜 서피스). 용도: 안내 블록, 온보딩, 빈 상태 패널.

**Agent Card** — bg `linear-gradient(135deg, #00704a, #1e3932)`, 흰 텍스트, 골드 `#cba258` 아이콘, radius 16px, padding 24px. 용도: AI 에이전트 패널 헤더, 에이전트 요약 카드.

### 배지 / 태그

- **Status**: bg `#00704a`, 흰 텍스트, 필, 11px/700 uppercase. 용도: "판독완료", "신규".
- **Agent**: bg `#cba258`, 텍스트 `#1e3932`, 필, 11px/700. 용도: "AI 초안", "에이전트 제안" — AI 산출물임을 항상 명시.
- **Subtle**: bg `#d4e9e2`, 텍스트 `#00704a`, 필, 11px/600. 용도: Modality(CT/MR/CR), 카테고리.
- **STAT**: bg `#c44d58`, 흰 텍스트, 필, 11px/700. 용도: 응급 검사 전용.

### 내비게이션

**상단 내비게이션 바** — 흰 bg, 높이 72px, 하단 1px `#e8e8e8`. 좌측 브랜드 마크(그린), 링크 `#1e1e1e` 14px/600, hover 그린 밑줄. 우측에 사용자 메뉴. 스크롤 시 스티키 + `backdrop-filter: blur(8px)`.

**뷰어 상단 바** — bg `#1e3932`, 툴 아이콘은 `#d4e9e2`, 활성 도구는 그린 필 하이라이트.

**Footer** — bg `#1e3932`, 헤딩 흰색, 링크 `#d4d4d4`.

### 토스트 / 알림

- **Default Toast**: bg `#1e3932`, 흰 텍스트, radius 8px, 섀도 elevated. 용도: "리포트가 전송되었습니다".
- **Inline Success Banner**: bg `#d4e9e2`, 텍스트 `#00704a`, 좌측 4px `#00704a` 보더. 용도: 지속형 성공/정보 메시지.

### 다이얼로그 / 모달

**중앙 모달** — 흰 bg, radius 16px, padding 32px, 섀도 `0 12px 40px rgba(30,57,50,0.24)`, 스크림 `rgba(30,57,50,0.6)`(그린 틴트). 용도: 확인, 리포트 전송 전 검토.

### 토글

**스위치** — 트랙 on `#00704a` / off `#d4d4d4`, 썸 `#ffffff` 20px. 용도: 설정.

## 5. 레이아웃 원칙

### 간격 시스템
- 기본 단위 4px, 주 리듬 8px. 공통 값: 4/8/12/16/24/32/48/64/96.
- 섹션 수직 패딩: 데스크톱 64–96px.
- 카드 콘텐츠 패딩: 16–32px.

### 그리드와 컨테이너
- 최대 콘텐츠 폭 1280px 중앙 정렬 (뷰어는 전체 폭 사용).
- 데스크톱 12컬럼, 24px 거터. 페이지 좌우 패딩: 모바일 24px / 데스크톱 48px.

### 여백 철학
- **여백이 곧 품질**: 밀집은 싸 보인다. 단, **워크리스트·데이터 테이블은 정보 밀도가 우선** — 행 높이 48px 기준으로 밀도를 확보하되, 테이블 밖 여백 리듬으로 숨을 쉰다.
- **영상이 주인공**: 뷰어에서 UI 크롬은 물러나고 캔버스가 최대 면적을 갖는다. 패널은 접을 수 있어야 한다.
- **교차 온기**: 섹션은 `#f1f8f4`와 `#fafafa`를 교차해 딱딱한 디바이더 없이 리듬을 만든다.

### 라운드 스케일
- 필(9999px): 버튼, 배지, 검색 필드, 토글, 칩.
- 컴포터블(16px): 카드, 모달, 이미지 컨테이너.
- 라지(20px): 피처 카드.
- 컴팩트(4–8px): 텍스트 입력, 토스트, 인라인 배너.

## 6. 깊이와 엘리베이션

| 레벨 | 처리 | 용도 |
|-------|-----------|-----|
| Flat (0) | 섀도 없음 | 페이지 섹션, 플랫 웜 카드 |
| Subtle (1) | `0 2px 8px rgba(30,57,50,0.08)` | 카드, 리스트 아이템 |
| Standard (2) | `0 4px 16px rgba(30,57,50,0.12)` | hover 카드, 스티키 내비 |
| Elevated (3) | `0 6px 20px rgba(30,57,50,0.25)` | 토스트, 드롭다운, 팝오버 |
| Modal (4) | `0 12px 40px rgba(30,57,50,0.24)` | 다이얼로그 |

**섀도 철학**: 섀도는 순흑이 아니라 딥 포레스트 틴트(`rgba(30,57,50,...)`)로. 위계의 1차 도구는 여백과 웜 서피스 레이어링이며, 섀도는 아껴 쓴다.

### 블러 효과
- 스티키 내비: `backdrop-filter: blur(8px)` + 반투명 흰 배경.
- 뷰어 오버레이(측정값 HUD 등): 반투명 딥 포레스트 + 미세 블러.

## 7. 해야 할 것 / 하지 말 것

### 해야 할 것
- 하우스 그린(`#00704a`)을 유일한 주요 액션·브랜드 색으로
- 필 버튼(9999px) — 시그니처 형태
- 웜 오프화이트 배경(`#f1f8f4`, `#fafafa`) — 순백 금지
- 뷰어는 다크(딥 포레스트 크롬 + 순흑 캔버스), 영상이 주인공
- 섀도·스크림은 딥 포레스트 틴트
- 골드(`#cba258`)는 AI 에이전트 표면에만, 베리(`#c44d58`)는 STAT에만
- UID·태그 등 식별자는 모노스페이스로
- 64–96px 섹션 여백으로 여유 있게 (테이블 내부는 밀도 우선)

### 하지 말 것
- 주요 액션에 외부 색(파랑·보라) 도입 금지 — 그린이 유일한 브랜드 색
- 각진 버튼 금지 — 필이 브랜드
- 순흑 스크림·섀도 금지 — 항상 그린 틴트
- AI 산출물을 배지 없이 사람 산출물처럼 표시 금지
- 골드를 에이전트 외 용도로, 베리를 STAT 외 용도로 사용 금지
- 차가운 지오메트릭 산스로 대체 금지
- 본문에 조밀한 행간(<1.5) 금지 (데이터 테이블 제외)

## 8. 반응형 동작

### 브레이크포인트
| 구간 | 폭 | 주요 변화 |
|------|-------|-------------|
| Mobile | <768px | 단일 컬럼, 햄버거 내비. 워크리스트는 카드 리스트로 전환 |
| Tablet | 768–1024px | 2–3컬럼, 확장 내비 |
| Desktop | >1024px | 풀 내비, 1280px 최대 폭. **판독 뷰어는 데스크톱 우선** |

- PACS의 주 사용 환경은 데스크톱(판독 모니터)이다. 뷰어는 1024px 미만에서 "조회용 간이 모드"로 동작하고, 측정 도구는 데스크톱에 우선 최적화한다.

### 터치 타깃
- 버튼 최소 높이 44px(모바일 48px), 리스트 행 최소 48px, 아이콘 버튼 44×44px.

### 축소 전략
- 상단 내비 → 모바일에서 햄버거 + 브랜드 마크.
- 워크리스트 테이블 → 모바일에서 스터디 카드 스택.
- 뷰어 사이드 패널(시리즈 목록, 에이전트 챗) → 하단 시트로 전환.

## 9. 에이전트 프롬프트 가이드

### 빠른 컬러 참조
- Primary CTA: House Green (`#00704a`) · Hover: Deep Forest (`#1e3932`)
- Accent: Spring Green (`#00a862`)
- Background: Warm White (`#f1f8f4`) / `#fafafa` · Dark Surface(뷰어): `#1e3932`, 캔버스 `#000000`
- Heading: `#1e1e1e` · Body: `#4a4a4a` · Caption: `#6b6b6b` · Placeholder: `#8c8c8c` · Border: `#d4d4d4`
- AI Agent 전용: Gold (`#cba258`) · STAT 전용: Berry (`#c44d58`)
- Success: `#00754a` · Error: `#e75b52`

### 컴포넌트 프롬프트 예시
- "주요 버튼: `#00704a` 배경, 흰 텍스트, Pretendard 14px 600 자간 0.3px, 완전 라운드 필, 12px 24px 패딩, 최소 높이 44px. Hover `#1e3932`."
- "워크리스트 행: 높이 48px, 하단 1px `#e8e8e8`, 환자명 14px 600 `#1e1e1e`, Accession Number는 JetBrains Mono 13px `#6b6b6b`, Modality는 `#d4e9e2` 서브틀 배지, STAT 검사는 `#c44d58` 배지."
- "에이전트 패널 헤더: `linear-gradient(135deg, #00704a, #1e3932)`, 흰 텍스트, 골드 `#cba258` 스파크 아이콘, radius 16px, padding 24px."
- "뷰어 툴바: bg `#1e3932`, 아이콘 `#d4e9e2`, 활성 도구는 `#00704a` 필 하이라이트, 캔버스는 `#000000`."
- "텍스트 입력: 흰 bg, 1px `#8c8c8c` 보더, radius 4px, 14px 16px 패딩. Focus 2px `#00704a` + `0 0 0 3px rgba(0,112,74,0.15)` 링."

### 반복 작업 가이드
1. `#00704a`가 유일한 주요 액션 색 — 파랑·보라 도입 금지
2. 버튼·배지·칩·검색·토글은 전부 필(9999px)
3. 배경은 웜 오프화이트, 뷰어만 다크
4. 섀도·스크림은 딥 포레스트 틴트
5. 골드는 에이전트 전용, 베리는 STAT 전용
6. 식별자는 모노스페이스
7. 여백은 넉넉하게, 데이터 테이블은 밀도 있게

## 10. 보이스 & 톤

DCMind는 **차분하고 정확한 동료**처럼 말한다 — 판독을 방해하지 않고, 필요한 정보를 정확한 순간에, 과장 없이. 따뜻하되 호들갑스럽지 않고, 전문적이되 차갑지 않다. AI 에이전트의 발화는 항상 자신이 AI임을 숨기지 않으며, 확신 수준을 정직하게 표현한다.

| 맥락 | 톤 |
|---|---|
| CTA | 행동 중심, 간결 ("판독 시작", "리포트 전송") |
| 성공 메시지 | 차분한 확인 ("리포트가 EMR로 전송되었습니다") |
| 오류 메시지 | 구체적, 비난 없음, 실행 가능 ("전송구문이 지원되지 않습니다 — 비압축 파일인지 확인하세요") |
| 에이전트 발화 | 정직한 확신 표현 ("과거 검사 2건을 찾았습니다", "이 초안은 검토가 필요합니다") |
| 빈 상태 | 다음 행동 제안 ("표시할 검사가 없습니다 — 필터를 조정해 보세요") |
| 임상 데이터 | 절대 장난스럽지 않게. 환자 정보 옆에서 유머 금지 |

**금지 표현**: 차가운 기계어("Error occurred", "Invalid input"), 과장·재촉("지금 확인하세요!"), 그리고 임상 맥락에서의 가벼운 감탄사. "실패했습니다" 대신 "~할 수 없습니다 — (이유/다음 행동)".

## 11. 브랜드 서사

DCMind는 DICOM + Mind — **영상 보관·전송(PACS)이라는 오래된 문제와, 대화형 AI 에이전트라는 새로운 도구의 결합**이다. 병원의 영상 워크플로우(오더 → 촬영 → 저장 → 판독 → 리포트)를 하나의 웹 시스템으로 관통시키되, 판독의 곁에 자연어로 부릴 수 있는 에이전트를 앉힌다.

디자인이 지켜야 할 두 가지 긴장:
1. **신뢰 vs 새로움** — 의료 시스템은 보수적 신뢰가 생명이지만, AI 에이전트는 새로움의 상징이다. 그래서 시스템 전체는 절제된 그린으로 안정감을 깔고, 에이전트에만 골드 액센트를 허락해 "특별하지만 격리된" 존재로 만든다.
2. **워크플로우 vs 판독** — 밝고 따뜻한 업무 화면과 어둡고 몰입적인 판독 화면. 두 세계는 같은 그린 패밀리로 묶인다.

DCMind가 거부하는 것: 차가운 순백-파랑 의료 클리셰, 데이터 밀집이 만드는 싸구려 느낌, 그리고 AI 산출물을 사람 것처럼 위장하는 디자인.

## 12. 원칙

1. **하나의 그린, 흔들림 없이.** `#00704a`가 브랜드다. 외부 색은 브랜드를 희석한다.
2. **따뜻함이 임상을 이긴다.** 서피스는 웜 오프화이트, 섀도는 그린 틴트, 서체는 휴머니스트. 차갑고 무서운 병원 소프트웨어가 되지 않는다.
3. **영상이 주인공, UI는 조연.** 뷰어에서 크롬은 물러난다. 워크리스트에서 데이터가 말한다.
4. **필 형태가 시그니처.** 버튼·배지·칩·검색 — 전부 라운드.
5. **여백이 품질이다.** 단, 데이터 테이블은 밀도가 곧 효율 — 구분해서 적용한다.
6. **에이전트는 골드로 격리한다.** AI가 만든 것은 항상 표시된다. 골드는 그 약속의 색이다.
7. **식별자는 모노스페이스.** UID·태그·해시가 한눈에 구별되는 것이 곧 안전이다.
8. **긴급함은 아껴 쓴다.** 베리(STAT)가 흔해지면 응급이 묻힌다. 강조는 희소해야 강하다.

## 13. 페르소나

*아래 페르소나는 PACS의 대표 사용자 역할을 요약한 가상의 인물이다.*

**판독의 (영상의학과 전문의).** 하루 수십 건의 스터디를 판독한다. 어두운 판독실, 듀얼 모니터. 워크리스트에서 다음 검사를 고르고, 뷰어에서 과거 검사와 비교하며, 리포트를 쓴다. 클릭 수와 로딩 시간에 극도로 민감하다. 에이전트에게 기대하는 것: 과거 검사 요약, 리포트 초안 — 단, 최종 판단은 언제나 자신의 것.

**방사선사 (촬영 담당).** 모달리티 옆에서 일한다. 워크리스트에서 오늘 배정된 검사를 확인하고, 촬영 후 영상이 PACS에 잘 올라갔는지 확인한다. 상태 전환(촬영완료)이 명확하고 빠르게 반영되는 것이 중요하다.

**PACS 관리자.** 시스템이 건강한지 본다 — 스토리지 사용량, 수신 실패, 무결성 검증 결과. 문제가 생기면 가장 먼저 알아야 하는 사람. 대시보드는 정확한 숫자와 명확한 경고가 전부다.

## 14. 상태

| 상태 | 처리 |
|---|---|
| **빈 상태 (워크리스트)** | `#4a4a4a` 본문 한 줄 ("표시할 검사가 없습니다 — 필터를 조정해 보세요") + 보조 액션. 텅 빈 화면 금지 |
| **빈 상태 (검색)** | `#6b6b6b` 캡션, 구체적 안내 ("해당 조건의 스터디가 없습니다") |
| **로딩 (첫 화면)** | `#f2f0eb` 스켈레톤 블록이 최종 레이아웃 형태로, 1.2s 셔머 |
| **로딩 (영상)** | 순흑 캔버스 위 그린 프로그레스, 시리즈 썸네일부터 점진 로드 |
| **로딩 (액션)** | 버튼 내 흰 스피너, 폭 유지, 이중 제출 방지 |
| **오류 (인라인 필드)** | 2px `#e75b52` 보더 + 아래 구체적 한 문장 |
| **오류 (토스트)** | `#1e3932` bg, 흰 14px, 3s 자동 소멸 |
| **오류 (차단)** | 네트워크/서버 장애 전용. 중앙 정렬 메시지 + 그린 필 재시도 버튼 |
| **성공 (업로드/저장)** | `#1e3932` 토스트 ("34개 인스턴스가 저장되었습니다") |
| **성공 (리포트 전송)** | 토스트 + 워크리스트 행 상태 배지가 "판독완료"로 전환 |
| **에이전트 응답 도착** | 골드 스파크 아이콘이 부드럽게 페이드인, 배지 "AI 초안" 부착. 절제된 존재감 |
| **비활성** | 버튼 bg `#d4d4d4`, 텍스트 `#8c8c8c`. 입력은 보더 유지로 기하 안정 |
| **선택 (행/옵션)** | 2px `#00704a` 보더 + `#f1f8f4` 필 + 그린 체크 |

## 15. 모션과 이징

**지속시간:**

| 토큰 | 값 | 용도 |
|---|---|---|
| `motion-instant` | 0ms | 토글 상태, reduced-motion 폴백 |
| `motion-fast` | 150ms | hover, focus, 버튼 프레스 |
| `motion-standard` | 250ms | 기본 — 카드 hover, 드롭다운, 탭 전환 |
| `motion-emphasis` | 400ms | 모달 등장, 에이전트 응답 도착 |
| `motion-page` | 350ms | 라우트 전환 |

**이징:**

| 토큰 | 커브 | 용도 |
|---|---|---|
| `ease-enter` | `cubic-bezier(0.0, 0.0, 0.2, 1)` | 등장 — 모달, 토스트, 시트 |
| `ease-exit` | `cubic-bezier(0.4, 0.0, 1, 1)` | 퇴장 |
| `ease-standard` | `cubic-bezier(0.4, 0.0, 0.2, 1)` | 양방향 — hover, 탭, 펼침 |
| `ease-agent` | `cubic-bezier(0.34, 1.56, 0.64, 1)` | **에이전트 응답 도착 전용** 미세 오버슈트. 다른 곳 금지 |

**시그니처 모션:**
1. **카드 hover-raise** — `translateY(-4px)` + 섀도 1→2 레벨, `motion-standard / ease-standard`.
2. **에이전트 응답 도착** — 골드 스파크가 `ease-agent` 오버슈트로 스케일 인(`motion-emphasis`). 시스템에서 유일하게 허락된 "생동" 모션 — AI의 존재를 알리되 판독을 방해하지 않는 크기로.
3. **상태 전환 펄스** — 워크리스트 행 상태 배지 변경 시 `scale 1 → 1.1 → 1` (`motion-fast`).
4. **모달 등장** — fade + scale 0.96→1, `ease-enter / motion-emphasis`, 그린 틴트 스크림 동기 페이드.
5. **Reduce motion** — `prefers-reduced-motion: reduce`에서 모든 토큰이 `motion-instant`로, 에이전트 도착은 단순 페이드로.

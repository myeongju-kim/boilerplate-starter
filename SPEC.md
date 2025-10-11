# Boilerplate Plugin Spec

## 목적
Boilerplate Plugin의 코드 품질을 자동 검증하기 위한 코드 리뷰 기준 문서입니다.  
이 문서는 Claude Code가 PR 생성 시 자동으로 참조하여 리뷰합니다.

---

## 검증 대상
- `/src/main/java/org/codegen/**`
- `/src/test/java/org/codegen/**`

---

## 클래스별 역할

| 클래스명 | 역할            | 주요 메서드 | 비고                  |
|-----------|---------------|-----------|---------------------|
| CodeGenExecutor | 코드 생성 오케스트레이션 | run() | 전체 흐름 제어            |
| SchemaReader | DB 스키마 읽는 기능  | read() | TableMeta 형태        |
| TemplateGenerator | 템플릿 생성        | generate() | TableMeta 기반 템플릿 생성 |
| OutputResolver | 파일 쓰기         | resolve() | 템플릿을 파일로 출력         |

---

## 코드 리뷰 규칙

### 1. 구조 및 책임 분리
- 모든 클래스는 단일 책임 원칙(SRP)을 따른다.
- 클래스 간 의존성은 명확히 분리되어야 하며, 순환 참조를 금지한다.

### 2. 네이밍
- 클래스명: PascalCase
- 메서드명/변수명: camelCase
- 테스트 클래스는 `*Test` 접미사를 사용한다.

### 3. 예외 처리
- 모든 파일 IO, 네트워크, 리플렉션 로직에는 명시적 예외 처리가 포함되어야 한다.
- 예외 메시지에는 민감정보(DB, API key 등)를 포함하지 않는다.

### 4. 로깅
- `LOGGER` 또는 `log`를 통해 로그를 남긴다.
- 로그 레벨은 `info`, `warn`, `error` 중 하나만 사용한다.
- DEBUG 로깅은 리뷰 시 제외한다.

### 5. 코드 컨벤션
- 메서드 길이는 30줄 이하를 권장한다.
- 한 파일 내 import 개수는 30개 이하.
- 불필요한 주석은 제거한다.
---

## 검증 명령어 예시

```bash
# 로컬 테스트 시
./gradlew clean build

# 코드 스타일 검증
./gradlew checkstyleMain checkstyleTest

# 수동 코드 리뷰 요청 (Claude)
gh workflow run ai-review.yml -f pr_number=123

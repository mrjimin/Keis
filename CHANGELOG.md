# 체인지로그

이 프로젝트의 모든 주요 변경 사항은 이 파일에 기록됩니다.

포맷은 [Keep a Changelog](https://keepachangelog.com/en/1.1.0/)을 기반으로 하며,  
이 프로젝트는 [Semantic Versioning](https://semver.org/spec/v2.0.0.html) 규칙을 따릅니다.

## 2.0.1 - 2026-04-10

### 추가
- `spring`(RestClient, WebClient) 지원

## 2.0.0 - 2026-04-02

### 추가
- `http4k` 지원
- `keisKtor`, `keisHttp4k`, `keis` 함수
- `school`, `dataRange` DSL

### 개선
- DSL 기반의 API 호출

### 수정
- API의 date/from/to/paging 파라미터 처리 로직을 Builder + DSL 중심으로 재작성

---

## 1.0.2 – 2026-03-30

### 추가
- `MealQueryBuilder` 및 DSL 확장 함수(`today()`, `thisWeek()`, `date()`, `dateRange()`, `page()`) 추가
- `MutableDateRange`와 `MutablePageable` 인터페이스 추가, Builder 내부에서 날짜와 페이징 제어 가능
- `MealQuery` immutable 데이터 클래스 추가, HttpRequestBuilder로 NEIS API 요청 파라미터 적용
- `KeisClient`에서 `requestRows()`와 `parseRows()` 공통 함수 추가, 모든 NEIS 엔드포인트 재사용 가능
- Builder 중심의 API 호출 패턴 도입, DSL로 직관적인 날짜/페이지 지정 가능

### 개선
- 기존 `KeisConfig`의 기본값(pIndex, pSize, stats 등)을 제거하고 Builder에서 직접 제어하도록 개선
- Query 객체를 immutable로 만들어 외부에서 직접 수정 불가능하게 변경
- Meal API 호출 시 Builder 기본값을 사용하고, DSL로만 override 가능하도록 구조 개선
- API Key는 Config에서만 관리하고, 나머지 옵션은 Builder/DSL로 통제

### 수정
- 기존 Meal API의 date/from/to/paging 파라미터 처리 로직을 Builder + DSL 중심으로 재작성
- HttpRequestBuilder parameter 세팅 방식을 Query의 `apply()`로 일원화

### 삭제
- Config에서 기본 페이징 및 날짜 관련 필드 삭제 (`pIndex`, `pSize`, `stats`)
- Builder 외부에서 Query 객체를 직접 수정할 수 있는 구조 제거
- `KeisConfig`삭제

---

## 1.0.1 - 2026-03-30

### 개선
- Query, Builder 구조 명확화

## 1.0.0 – 2026-03-30

### 추가
- School / Meal / Schedule / Timetable API 전면 구현
- Kotlin DSL 기반 Query Builder 시스템 도입
- SchoolContext를 통한 API 체이닝 구조 추가
- 시간표 누락 교시 자동 보완 기능 (`fillMissing`)
- 날짜 처리 DSL (`today`, `thisWeek`, `date`, `dateRange`)
- DayNightCourse, SchoolCourse 등 enum 기반 필터링 지원
- Example 프로젝트 및 기능별 예제 코드 추가
- README 및 문서 구조 정리

### 개선
- 모든 API를 Query + Builder 구조로 통일
- Simple API + DSL API 병행 지원 구조로 개선
- DTO → Domain 변환 흐름 일관성 확보
- 날짜 포맷 처리 방식 개선 (`toYmd` 확장 함수 도입)
- API 사용성 및 가독성 향상 (context 기반 접근)
- 내부 fetch / execute 로직 분리로 구조 명확화

### 수정
- 잘못된 변수 네이밍 수정 (SchoolType → SchoolCourse 등)
- enum → API 요청 값 매핑 로직 보완
- 날짜 파라미터 포맷 오류 수정
- 일부 DSL 동작 누락 및 버그 수정
- fillMissing 로직 안정성 개선 (정렬 및 null 처리 보완)

### 삭제
- 불필요한 직접 파라미터 기반 API 구조 일부 제거
- 중복된 request 로직 및 legacy 코드 정리
- 전역 dateFormat 의존 제거

---

## 0.0.3 – 2026-03-29

### 추가
- `meal` 기능 출시

### 개선
- 내부 구조 리팩터링
- 성능 최적화

---

## 0.0.21 – 2026-03-29

### 개선
- 내부 구조 리팩터링
- 성능 최적화

---

## 0.0.2 – 2026-03-28

### 추가
- `timeTable` 기능 출시

### 개선
- API 호출 방식 개선

---

## 0.0.1 – 2026-03-22

### 추가
- 기본 기능 구현

### 삭제
- 초기 버전 출시
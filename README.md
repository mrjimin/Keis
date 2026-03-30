# Keis

Kotlin + Ktor 기반 NEIS(교육청) API 클라이언트 라이브러리

[![](https://jitpack.io/v/mrjimin/Keis.svg)](https://jitpack.io/#mrjimin/Keis)
[![GitHub License](https://img.shields.io/github/license/mrjimin/Keis?style=flat-square)](LICENSE)
[![Kotlin](https://img.shields.io/badge/kotlin-2.3.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Ktor](https://img.shields.io/badge/ktor-3.4.1-087CFA.svg?logo=ktor)](https://ktor.io/)
[![DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/mrjimin/Keis)

---

## ✨ Features

- 코루틴 기반 비동기 API
- 간단한 Client 호출 구조
- DTO → Domain 자동 변환
- Kotlin 친화적 DSL API 설계
- 시간표 누락 교시 자동 채우기
- 날짜 / 학년 / 반 / 급식 타입 등 다양한 조회 지원

---

## 📦 Installation

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.mrjimin:Keis:1.0.0")
}
```

---

## 🚀 Quick Start
```kotlin
suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")

    // 학교 컨텍스트 생성
    val context = client.schoolContext("우석고") ?: return

    // Meal
    val lunch = context.meal(MealType.LUNCH) {
        today()
    }

    // Timetable
    val timetable = context.timetable {
        grade(2)
        classNumber(3)
        fillMissing(true)
    }
    
    // Schedule
    val schedules = context.schedule()

}
```
👉 자세한 사용법은 아래 API 또는 Example을 참고하세요.

---

## 📚 API
### 🏫 [School](example/src/main/kotlin/com/github/mrjimin/keis/example/SchoolExample.kt)
```kotlin
client.school("학교명"): School?
client.schools("학교명"): List<School>
```

### 📚 [Timetable](example/src/main/kotlin/com/github/mrjimin/keis/example/TimetableExample.kt)
```kotlin
context.timetable {
  grade(1)
  classNumber(2)
  fillMissing()
  dateRange(from, to)
}
```

### 🍽️ [Meal](example/src/main/kotlin/com/github/mrjimin/keis/example/MealExample.kt)
```kotlin
context.meal(MealType.LUNCH) {
  today()
}

context.meal(MealType.ALL) {
  thisWeek()
}
```

### 📅 [Schedule](example/src/main/kotlin/com/github/mrjimin/keis/example/ScheduleExample.kt)
```kotlin
context.schedule {
    today()
    dateRange(from, to)
    dayNightCourse(DayNightCourse.DAY)
    schoolCourse(SchoolCourse.GENERAL)
}
```

---

## 📎 References

- NEIS Open API: https://open.neis.go.kr
- Other implementations:
    - https://github.com/nnnlog/neis
    - https://github.com/kimcore/neis.kt

---

## 🤝 Contributing

Keis는 누구나 기여할 수 있습니다! 🎉

### 1. 기여 방법
   1. 이 레포지토리를 Fork 합니다. 
   2. 새로운 브랜치를 생성합니다. 
   3. 코드를 수정합니다. 
   4. Pull Request를 생성합니다.

### 2. 코드 스타일
 - Kotlin 스타일 가이드를 따릅니다. 
 - 불필요한 null 사용을 지양합니다.
 - Domain / DTO 분리를 유지합니다.

### 3. 기타
 - 작은 수정도 환영합니다 🙌
 - 문서 개선도 훌륭한 기여입니다!
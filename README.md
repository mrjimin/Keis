# Keis

Kotlin + Ktor 기반 NEIS(교육청) API 클라이언트 라이브러리

[![](https://jitpack.io/v/mrjimin/Keis.svg)](https://jitpack.io/#mrjimin/Keis)
![GitHub License](https://img.shields.io/github/license/mrjimin/Keis?style=flat-square)
[![Kotlin](https://img.shields.io/badge/kotlin-2.3.20-blue.svg?logo=kotlin)](http://kotlinlang.org)

---

## ✨ Features

* 코루틴 기반 비동기 API
* 간단한 Client 호출 (`client.school(...)`)
* DTO → Domain 자동 변환
* 시간표 누락 교시 자동 보정 (`fillMissing`)
* Kotlin 친화적인 API 설계

---

## 📦 Installation

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.mrjimin:Keis:0.0.1")
}
```

---

## 🚀 Quick Start
```kotlin
val client = KeisClient("YOUR_API_KEY")

val school = client.school("우석고") // 우석고등학교
val timetable = school?.let {
    client.timetableBySchool(it, fillMissing = true)
}

println(timetable)
```

---

## 📚 API
### [School](example/src/main/kotlin/com/github/mrjimin/keis/example/SchoolExample.kt)
```kotlin
client.schools(name: String): List<School>
client.school(name: String): School?
client.schoolByCode(educationOffice: EducationOffice, schoolCode: Int): School?
```
### [Timetable](example/src/main/kotlin/com/github/mrjimin/keis/example/TimetableExample.kt)
```kotlin
client.timetable(...): List<Timetable>
client.timetableBySchool(...): List<Timetable>
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
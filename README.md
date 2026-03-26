# Keis

Kotlin + Ktor 기반 NEIS(교육청) API 클라이언트 라이브러리

[![](https://jitpack.io/v/mrjimin/Keis.svg)](https://jitpack.io/#mrjimin/Keis)
![GitHub License](https://img.shields.io/github/license/mrjimin/Keis?style=flat-square)
[![Kotlin](https://img.shields.io/badge/kotlin-2.3.20-blue.svg?logo=kotlin)](http://kotlinlang.org)

---

## ✨ Features

* 코루틴 기반 비동기 처리
* 간단한 API 호출 구조
* 병렬 요청 지원
* 타입 안전 DTO 제공

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

🚀 Quick Start
```kotlin
val client = KeisClient("YOUR_API_KEY")

val school = client.schoolInfo("우석고등학교")
println(school)
```

---

## 📚 API

### [SchoolInfo](https://github.com/mrjimin/Keis/blob/main/example/src/main/kotlin/com/github/mrjimin/keis/example/SchoolInfoExample.kt)

## 참고
- NEIS Open API 공식 문서: [https://open.neis.go.kr](https://open.neis.go.kr)
- 다른 구현 참고: [nnnlog/neis](https://github.com/nnnlog/neis), [kimcore/neis.kt](https://github.com/kimcore/neis.kt)
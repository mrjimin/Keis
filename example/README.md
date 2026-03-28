# Examples

이 폴더는 Keis Kotlin Client 사용 예제를 포함합니다.

## School
학교 조회

```kotlin
val client = KeisClient("YOUR_API_KEY")

// 여러 학교 조회
val schools = client.schools("전주고") // 전주고등학교 , 전주고등학교부설방송통신고등학교

// 단일 학교 조회
val school = client.school("우석고") // 우석고등학교

// SchoolContext 변환
val context = school!!.toContext(client)
val schoolContext = client.schoolContext("우석고")
```

### Timetable
시간표 조회

```kotlin
// 기본 조회
val timetable = context.timetable()

// 누락 교시 채우기
val filled = context.timetable {
    fillMissing()
}

// 특정 학년 / 반
val classTimetable = context.timetable {
    grade(1)
    classNumber(1)
    fillMissing()
}

// 날짜 범위 지정
val range = context.timetable {
    dateRange(LocalDate.now(), LocalDate.now().plusDays(2))
}
```
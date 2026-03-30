# Examples

이 폴더는 Keis Kotlin Client 사용 예제를 포함합니다.

## 🏫 School
학교 조회

```kotlin
// 여러 학교 조회
val schools = client.schools("전주고")
schools.forEach { println(it.name) }
// 예: 전주고등학교, 전주고등학교부설방송통신고등학교

// 단일 학교 조회
val school = client.school("우석고")
println(school?.name) // 우석고등학교

// SchoolContext 생성 (추천)
val context = client.schoolContext("우석고")
```

## 📚 Timetable
시간표 조회

```kotlin
// 기본 조회 (이번 주)
val timetable = context.timetable()

// 누락 교시 자동 채우기
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

## 🍽️ Meal
급식 조회

```kotlin
// 기본 조회 (이번 주)
val meals = context.meal()

// 특정 급식 (점심)
val lunch = context.meal(MealType.LUNCH)

// 오늘 급식
val today = context.meal {
    today()
}

// 날짜 범위 지정
val range = context.meal(MealType.ALL) {
    dateRange(LocalDate.now(), LocalDate.now().plusDays(2))
}
```

## 📅 Schedule
학사일정 조회

```kotlin
// 기본 조회 (이번 주)
val schedules = context.schedule()

// 오늘 일정
val today = context.schedule {
    today()
}

// 날짜 범위
val range = context.schedule {
    dateRange(LocalDate.now(), LocalDate.now().plusDays(7))
}

// 주야 과정 필터
val dayCourse = context.schedule {
    dayNightCourse(DayNightCourse.DAY)
}

// 학교 과정 필터
val course = context.schedule {
    schoolCourse(SchoolCourse.GENERAL)
}
```
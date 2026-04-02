# Examples

이 폴더는 **Keis Kotlin Client** 사용 예제를 포함합니다.

---

## 🏫 School

학교 조회 예제입니다.

```kotlin
// 이름으로 학교 조회
val school = client.school("우석고")

// 여러 학교 조회 (이름 필터)
val schools = client.schools {
    name("우석")
}

// 교육청 + 코드로 학교 조회
val schoolByCode = client.school(EducationOffice.JEONBUK, 8321094)

// DSL 사용
val filteredSchools = client.schools {
    school {
        office(EducationOffice.JEONBUK)
        code(8321094)
    }
}

// SchoolContext 생성
val context = client.schoolContext("우석고")

val asContext = school?.asContext(client)
```

---

## 🍽️ Meal

급식 조회 예제입니다.

```kotlin
// 급식 조회
val meals = context.meals()

// 특정 급식 (점심)
val lunch = context.meals {
    type(MealType.LUNCH)
}

// 오늘 급식
val todayMeal = context.meals {
    dateRange {
        today()
    }
    // today() 사용가능
}

// 특정 날짜 급식
val specificDate = context.meals {
    dateRange {
        single(LocalDate.of(2026, 4, 2))
    }
}

// 날짜 범위 급식
val rangeMeals = context.meals {
    dateRange {
        range(LocalDate.now(), LocalDate.now().plusDays(3))
    }
}

// 이번 주 급식
val weekMeals = context.meals {
    dateRange {
        thisWeek()
    }
}
```

---

## 📚 Timetable

시간표 조회 예제입니다.

```kotlin
// 시간표 조회
val timetable = context.timetables()

// 오늘 시간표
val todayTimetable = context.timetables {
    dateRange {
        today()
    }
    // today() 사용가능
}

// 특정 날짜 시간표
val specificDateTimetable = context.timetables {
    dateRange {
        single(LocalDate.of(2026, 3, 30))
    }
}

// 특정 반 시간표
val classTimetable = context.timetables {
    grade(2)
    classNumber(5)
}

// 빈 교시 채우기
val filledTimetable = context.timetables {
    grade(2)
    classNumber(5)
    fillMissing() // fillMissing(boolean = true)
}

// 기간 + 필터
val advancedTimetable = context.timetables {
    grade(2)
    classNumber(5)
    dateRange {
        range(LocalDate.now(), LocalDate.now().plusDays(5))
    }
    fillMissing(true)
}
```

---

## 📅 Schedule

학사일정 조회 예제입니다.

```kotlin
    // 일정 조회
val schedules = context.schedules()

// 오늘 일정
val todaySchedule = context.schedules {
    dateRange {
        today()
    }
    // today() 사용가능
}

// 특정 날짜 일정
val specificSchedule = context.schedules {
    dateRange {
        single(LocalDate.of(2026, 4, 2))
    }
}

// 주야 과정 필터
val dayCourseSchedule = context.schedules {
    dayNightCourse(DayNightCourse.DAY)
}

// 학교 과정 필터
val schoolCourseSchedule = context.schedules {
    schoolCourse(SchoolCourse.GENERAL)
}
```
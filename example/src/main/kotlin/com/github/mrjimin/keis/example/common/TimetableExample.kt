package com.github.mrjimin.keis.example.common

import com.github.mrjimin.keis.core.api.school.SchoolContext
import java.time.LocalDate

suspend fun timetableExample(context: SchoolContext) {

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
}
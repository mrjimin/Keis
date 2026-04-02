package com.github.mrjimin.keis.example.common

import com.github.mrjimin.keis.core.api.school.SchoolContext
import com.github.mrjimin.keis.core.enums.DayNightCourse
import com.github.mrjimin.keis.core.enums.SchoolCourse
import java.time.LocalDate

suspend fun scheduleExample(context: SchoolContext) {

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

}
package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.school.schoolContext
import com.github.mrjimin.keis.enums.DayNightCourse
import com.github.mrjimin.keis.enums.SchoolCourse
import com.github.mrjimin.keis.internal.dsl.dateRange
import com.github.mrjimin.keis.internal.dsl.today
import java.time.LocalDate

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

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
}
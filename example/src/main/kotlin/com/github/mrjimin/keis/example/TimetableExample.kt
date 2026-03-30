package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.internal.dsl.date
import com.github.mrjimin.keis.internal.dsl.dateRange
import com.github.mrjimin.keis.internal.dsl.today
import com.github.mrjimin.keis.api.school.schoolContext
import java.time.LocalDate

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

    // 기본 시간표 (이번 주)
    val timetable = context.timetable()

    // 오늘 시간표
    val today = context.timetable {
        today()
    }

    // 특정 날짜
    val specific = context.timetable {
        date(LocalDate.of(2026, 3, 30))
    }

    // 특정 반 시간표
    val classTimetable = context.timetable {
        grade(2)
        classNumber(3)
    }

    // 빈 교시 채우기
    val filled = context.timetable {
        grade(2)
        classNumber(3)
        fillMissing(true)
    }

    // 기간 + 필터
    val advanced = context.timetable {
        grade(1)
        classNumber(2)
        dateRange(LocalDate.now(), LocalDate.now().plusDays(5))
        fillMissing(true)
    }
}
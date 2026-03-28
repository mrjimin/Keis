package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.schoolContext
import java.time.LocalDate

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

    // 기본 조회
    val timetable = context.timetable()

    // 누락 교시 채우기
    val filled = context.timetable {
        fillMissing()
    }

    // 특정 학년/반
    val classTimetable = context.timetable {
        grade(1)
        classNumber(1)
        fillMissing()
    }

    // 날짜 범위 지정
    val range = context.timetable {
        dateRange(LocalDate.now(), LocalDate.now().plusDays(2))
    }
}
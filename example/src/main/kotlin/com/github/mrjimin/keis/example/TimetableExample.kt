package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.school
import com.github.mrjimin.keis.api.timetable
import java.time.LocalDate

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")

    val school = client.school("우석고등학교")
        ?: return println("학교를 찾을 수 없습니다.")

    // 기본
    val timetable = client.timetable(school)

    println("\n[기본 시간표]")
    timetable.forEach {
        println("${it.date} ${it.period}교시: ${it.content}")
    }

    // DSL 사용
    val filled = client.timetable(school) {
        fillMissing()
    }

    println("\n[누락 교시 보정]")
    filled.forEach {
        println("${it.date} ${it.period}교시: ${it.content ?: "없음"}")
    }

    val classTimetable = client.timetable(school) {
        grade(1)
        classNumber(1)
        fillMissing()
    }

    println("\n[1학년 1반]")
    classTimetable.forEach {
        println("${it.date} ${it.period}교시: ${it.content ?: "없음"}")
    }

    val range = client.timetable(school) {
        dateRange(LocalDate.now(), LocalDate.now().plusDays(2))
    }

    println("\n[기간 조회]")
    range.forEach {
        println("${it.date} ${it.period}교시: ${it.content}")
    }
}
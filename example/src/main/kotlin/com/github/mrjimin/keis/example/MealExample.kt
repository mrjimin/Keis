package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.school.schoolContext
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.internal.dsl.date
import com.github.mrjimin.keis.internal.dsl.dateRange
import com.github.mrjimin.keis.internal.dsl.thisWeek
import com.github.mrjimin.keis.internal.dsl.today
import java.time.LocalDate

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

    // 기본 급식 조회 (이번 주)
    val meals = context.meal()

    // 특정 급식 (점심)
    val lunch = context.meal(MealType.LUNCH)

    // 오늘 급식
    val today = context.meal {
        today()
    }

    // 날짜 지정
    val specificDate = context.meal {
        date(LocalDate.of(2026, 3, 30))
    }

    // 날짜 범위
    val range = context.meal(MealType.ALL) {
        dateRange(LocalDate.now(), LocalDate.now().plusDays(3))
    }

    // 이번 주
    val week = context.meal {
        thisWeek()
    }
}
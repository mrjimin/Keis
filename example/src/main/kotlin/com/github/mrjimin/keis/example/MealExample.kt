package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.schoolContext
import com.github.mrjimin.keis.enums.MealType
import java.time.LocalDate

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

    // 기본 조회
    val mealsToday = context.meal()

    // 특정 급식 시간 조회
    val lunch = context.meal(MealType.LUNCH)

    // 날짜 범위 지정
    val mealsRange = context.meal(MealType.ALL) {
        dateRange(LocalDate.now(), LocalDate.now().plusDays(2))
    }
}
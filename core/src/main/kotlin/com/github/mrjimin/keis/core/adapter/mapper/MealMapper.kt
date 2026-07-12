package com.github.mrjimin.keis.core.adapter.mapper

import com.github.mrjimin.keis.core.adapter.dto.MealResponseDto
import com.github.mrjimin.keis.core.domain.Meal
import com.github.mrjimin.keis.core.internal.toPlainText
import com.github.mrjimin.keis.core.internal.toYmd

fun Meal.toDto(): MealResponseDto =
    MealResponseDto(
        office.code,
        office.name,
        schoolCode,
        schoolName,
        type.name,
        date.toYmd(),
        mealCount,
        content.toPlainText(),
        origin.toPlainText(),
        nutrition.toPlainText(),
        calories,
        loadDateTime.toYmd(),
    )
package com.github.mrjimin.keis.core.internal.mapper

import com.github.mrjimin.keis.core.domain.Meal
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.domain.enums.MealType
import com.github.mrjimin.keis.core.internal.cleanHtml
import com.github.mrjimin.keis.core.internal.response.MealResponse
import com.github.mrjimin.keis.core.internal.toCalories
import com.github.mrjimin.keis.core.internal.toLocalDate

internal fun MealResponse.toDomain(): Meal =
    Meal(
        EducationOffice.from(officeCode, officeName),
        schoolCode,
        schoolName,
        MealType.from(mealTypeText),
        dateText.toLocalDate(),
        mealCountText.toInt(),
        content.cleanHtml(),
        origin.cleanHtml(),
        nutrition.cleanHtml(),
        caloriesText.toCalories(),
        loadDateTime.toLocalDate()
    )
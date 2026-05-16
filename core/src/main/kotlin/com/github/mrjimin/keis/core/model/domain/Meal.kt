package com.github.mrjimin.keis.core.model.domain

import com.github.mrjimin.keis.core.enums.EducationOffice
import com.github.mrjimin.keis.core.enums.MealType
import java.time.LocalDate

data class Meal(
    val office: EducationOffice,
    val schoolCode: Int,
    val schoolName: String,
    val type: MealType,
    val date: LocalDate,
    val mealCount: Int,
    val content: String,
    val origin: String,
    val nutrition: String,
    val calories: Double,
)

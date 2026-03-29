package com.github.mrjimin.keis.model.domain

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import java.time.LocalDate

data class Meal(
    val office: EducationOffice,
    val schoolCode: String,
    val schoolName: String,
    val type: MealType,
    val date: LocalDate,
    val mealCount: Int,
    val content: String,
    val origin: String,
    val nutrition: String,
    val calories: Double,
)

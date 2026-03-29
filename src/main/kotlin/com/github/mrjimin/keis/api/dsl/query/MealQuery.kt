package com.github.mrjimin.keis.api.dsl.query

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import java.time.LocalDate

data class MealQuery(
    val office: EducationOffice,
    val schoolCode: String,
    val mealType: MealType,
    val from: LocalDate,
    val to: LocalDate,
)
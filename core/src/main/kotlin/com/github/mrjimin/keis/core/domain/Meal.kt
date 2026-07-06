package com.github.mrjimin.keis.core.domain

import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.domain.enums.MealType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Meal(
    val office: EducationOffice,
    val schoolCode: Int,
    val schoolName: String,
    val type: MealType,
    @Contextual
    val date: LocalDate,
    val mealCount: Int,
    val content: String,
    val origin: String,
    val nutrition: String,
    val calories: Double,
    @Contextual
    val loadDateTime: LocalDate
)
package com.github.mrjimin.keis.core.adapter.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealResponseDto(
    val officeCode: String,
    val officeName: String,
    val schoolCode: Int,
    val schoolName: String,
    val type: String,
    val date: String,
    val mealCount: Int,
    val content: String,
    val origin: String,
    val nutrition: String,
    val calories: Double,
    val loadDateTime: String
)
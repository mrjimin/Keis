package com.github.mrjimin.keis.core.adapter.dto

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponseDto(
    val officeCode: String,
    val officeName: String,
    val schoolCode: Int,
    val schoolName: String,
    val date: String,
    val eventName: String,
    val eventContent: String? = null,
    val targetGrades: List<Int>,
    val loadDateTime: String
)
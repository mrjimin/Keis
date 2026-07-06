package com.github.mrjimin.keis.core.adapter.dto

import kotlinx.serialization.Serializable

@Serializable
data class TimetableResponseDto(
    val officeCode: String,
    val officeName: String,
    val schoolCode: Int,
    val schoolName: String,
    val grade: Int,
    val classNumber: Int,
    val period: Int,
    val year: Int,
    val semester: Int,
    val date: String,
    val order: String? = null,
    val major: String? = null,
    val classroom: String? = null,
    val content: String? = null,
    val loadDateTime: String
)
package com.github.mrjimin.keis.core.adapter.dto

import kotlinx.serialization.Serializable

@Serializable
data class SchoolResponseDto(
    val officeCode: String,
    val officeName: String,
    val code: Int,
    val name: String,
    val nameEn: String?,
    val type: String,
    val foundationType: String,
    val gender: String,
    val location: String,
    val businessType: String,
    val homepageUrl: String,
    val dayNightCourse: String,
    val establishedDate: String,
    val loadDateTime: String
)
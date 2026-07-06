package com.github.mrjimin.keis.core.domain

import com.github.mrjimin.keis.core.domain.enums.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class School(
    val office: EducationOffice,
    val code: Int,
    val name: String,
    val nameEn: String?,
    val type: SchoolType,
    val foundationType: FoundationType,
    val gender: SchoolGender,
    val location: String,
    val businessType: BusinessType,
    val homepageUrl: String,
    val dayNightCourse: DayNightCourse,
    @Contextual
    val establishedDate: LocalDate,
    @Contextual
    val loadDateTime: LocalDate
)
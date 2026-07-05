package com.github.mrjimin.keis.core.domain

import com.github.mrjimin.keis.core.domain.enums.*
import java.time.LocalDate

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
    val establishedDate: LocalDate,
    val loadDateTime: LocalDate
)
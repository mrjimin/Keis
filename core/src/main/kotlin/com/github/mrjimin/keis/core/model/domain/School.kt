package com.github.mrjimin.keis.core.model.domain

import com.github.mrjimin.keis.core.enums.*
import java.time.LocalDate

data class School(
    val office: EducationOffice,
    val code: Int,
    val name: String,
    val nameEn: String?,
    val type: SchoolType,
    val gender: SchoolGender,
    val location: String,
    val businessType: BusinessType,
    val homepageUrl: String,
    val dayNightCourse: DayNightCourse,
    val establishedDate: LocalDate
)

package com.github.mrjimin.keis.model.domain

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolType

data class School(
    val office: EducationOffice,
    val code: String,
    val name: String,
    val nameEn: String?,
    val type: SchoolType,
    val location: String,
    val businessType: String?,
    val homepageUrl: String,
)

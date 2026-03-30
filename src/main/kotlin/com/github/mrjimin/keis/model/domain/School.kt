package com.github.mrjimin.keis.model.domain

import com.github.mrjimin.keis.enums.BusinessType
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolGender
import com.github.mrjimin.keis.enums.SchoolType

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
)

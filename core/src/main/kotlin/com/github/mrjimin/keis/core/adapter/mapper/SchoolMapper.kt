package com.github.mrjimin.keis.core.adapter.mapper

import com.github.mrjimin.keis.core.adapter.dto.SchoolResponseDto
import com.github.mrjimin.keis.core.domain.School

fun School.toDto() = SchoolResponseDto(
    officeCode = office.code,
    officeName = office.name,
    code = code,
    name = name,
    nameEn = nameEn,
    type = type.name,
    foundationType = foundationType.name,
    gender = gender.name,
    location = location,
    businessType = businessType.name,
    homepageUrl = homepageUrl,
    dayNightCourse = dayNightCourse.name,
    establishedDate = establishedDate.toString(),
    loadDateTime = loadDateTime.toString()
)
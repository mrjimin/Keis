package com.github.mrjimin.keis.core.adapter.mapper

import com.github.mrjimin.keis.core.adapter.dto.SchoolResponseDto
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.internal.toYmd

fun School.toDto() = SchoolResponseDto(
    office.code,
    office.name,
    code,
    name,
    nameEn,
    type.name,
    foundationType.name,
    gender.name,
    location,
    businessType.name,
    homepageUrl,
    dayNightCourse.name,
    establishedDate.toYmd(),
    loadDateTime.toYmd()
)
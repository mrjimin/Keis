package com.github.mrjimin.keis.core.internal.mapper

import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.enums.*
import com.github.mrjimin.keis.core.internal.response.SchoolResponse
import com.github.mrjimin.keis.core.internal.toLocalDate

internal fun SchoolResponse.toDomain(): School =
    School(
        EducationOffice.from(officeCode, officeName),
        code,
        name,
        nameEn,
        SchoolType.from(typeText),
        FoundationType.from(foundationTypeText),
        SchoolGender.from(genderText),
        location,
        BusinessType.from(businessTypeText),
        homepageUrl,
        DayNightCourse.from(dayNightCourseText),
        establishedDateText.toLocalDate(),
        loadDateTime.toLocalDate()
    )
package com.github.mrjimin.keis.core.internal.mapper

import com.github.mrjimin.keis.core.domain.Timetable
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.internal.response.TimetableResponse
import com.github.mrjimin.keis.core.internal.toLocalDate

internal fun TimetableResponse.toDomain(): Timetable =
    Timetable(
        EducationOffice.from(officeCode, officeName),
        schoolCode,
        schoolName,
        grade,
        classNumber,
        period,
        year,
        semester,
        dateText.toLocalDate(),
        order,
        major,
        classroom,
        content,
        loadDateTime.toLocalDate()
    )
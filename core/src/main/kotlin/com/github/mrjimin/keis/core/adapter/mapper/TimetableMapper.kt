package com.github.mrjimin.keis.core.adapter.mapper

import com.github.mrjimin.keis.core.adapter.dto.TimetableResponseDto
import com.github.mrjimin.keis.core.domain.Timetable
import com.github.mrjimin.keis.core.internal.toYmd

fun Timetable.toDto(): TimetableResponseDto =
    TimetableResponseDto(
        office.code,
        office.name,
        schoolCode,
        schoolName,
        grade,
        classNumber,
        period,
        year,
        semester,
        date.toYmd(),
        order,
        major,
        classroom,
        content,
        loadDateTime.toYmd()
    )
package com.github.mrjimin.keis.core.adapter.mapper

import com.github.mrjimin.keis.core.adapter.dto.ScheduleResponseDto
import com.github.mrjimin.keis.core.domain.Schedule
import com.github.mrjimin.keis.core.internal.toYmd

fun Schedule.toDto(): ScheduleResponseDto =
    ScheduleResponseDto(
        office.code,
        office.name,
        schoolCode,
        schoolName,
        date.toYmd(),
        eventName,
        eventContent,
        targetGrades,
        loadDateTime.toYmd()
    )
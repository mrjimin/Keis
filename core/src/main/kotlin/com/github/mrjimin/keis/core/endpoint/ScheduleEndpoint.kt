package com.github.mrjimin.keis.core.endpoint

import com.github.mrjimin.keis.core.domain.Schedule
import com.github.mrjimin.keis.core.internal.mapper.toDomain
import com.github.mrjimin.keis.core.internal.response.ScheduleResponse

object ScheduleEndpoint : Endpoint<ScheduleResponse, Schedule>(
    "SchoolSchedule",
    ScheduleResponse.serializer(),
    ScheduleResponse::toDomain
)
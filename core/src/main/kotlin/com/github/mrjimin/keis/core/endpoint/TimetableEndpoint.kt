package com.github.mrjimin.keis.core.endpoint

import com.github.mrjimin.keis.core.domain.Timetable
import com.github.mrjimin.keis.core.domain.enums.SchoolType
import com.github.mrjimin.keis.core.internal.endpoint.endpoint
import com.github.mrjimin.keis.core.internal.mapper.toDomain
import com.github.mrjimin.keis.core.internal.response.TimetableResponse

class TimetableEndpoint(
    type: SchoolType
) : Endpoint<TimetableResponse, Timetable>(
    type.endpoint(),
    TimetableResponse.serializer(),
    TimetableResponse::toDomain
)
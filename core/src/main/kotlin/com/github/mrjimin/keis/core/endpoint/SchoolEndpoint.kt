package com.github.mrjimin.keis.core.endpoint

import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.internal.mapper.toDomain
import com.github.mrjimin.keis.core.internal.response.SchoolResponse

object SchoolEndpoint : Endpoint<SchoolResponse, School>(
    "schoolInfo",
    SchoolResponse.serializer(),
    SchoolResponse::toDomain
)
package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.builder.ScheduleQueryBuilder
import com.github.mrjimin.keis.api.dsl.query.ScheduleQuery
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.model.domain.Schedule
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.dto.ScheduleDTO
import io.ktor.client.request.*

suspend fun KeisClient.schedule(
    office: EducationOffice,
    schoolCode: Int
): List<Schedule> =
    fetchSchedule(
        ScheduleQueryBuilder(office, schoolCode).build()
    )

suspend fun KeisClient.schedule(
    school: School
): List<Schedule> =
    schedule(school.office, school.code)

suspend fun KeisClient.schedule(
    office: EducationOffice,
    schoolCode: Int,
    block: ScheduleQueryBuilder.() -> Unit
): List<Schedule> {
    val query = ScheduleQueryBuilder(office, schoolCode)
        .apply(block)
        .build()

    return fetchSchedule(query)
}

suspend fun KeisClient.schedule(
    school: School,
    block: ScheduleQueryBuilder.() -> Unit
): List<Schedule> =
    schedule(school.office, school.code, block)

private suspend fun KeisClient.fetchSchedule(
    query: ScheduleQuery,
    block: HttpRequestBuilder.() -> Unit = {}
): List<Schedule> {
    return requestRows<ScheduleDTO>("SchoolSchedule") {
        query.apply(this)
        block()
    }.map { it.toDomain() }
}
package com.github.mrjimin.keis.api.timetable

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable
import com.github.mrjimin.keis.model.dto.TimetableDTO
import io.ktor.client.request.*

suspend fun KeisClient.timetable(
    office: EducationOffice,
    schoolCode: Int,
    schoolType: SchoolType
): List<Timetable> =
    executeTimetable(
        TimetableQueryBuilder(office, schoolCode, schoolType).build()
    )

suspend fun KeisClient.timetable(
    school: School
): List<Timetable> =
    timetable(school.office, school.code, school.type)

suspend fun KeisClient.timetable(
    office: EducationOffice,
    schoolCode: Int,
    schoolType: SchoolType,
    block: TimetableQueryBuilder.() -> Unit = {}
): List<Timetable> {
    val query = TimetableQueryBuilder(office, schoolCode, schoolType)
        .apply(block)
        .build()

    return executeTimetable(query)
}

suspend fun KeisClient.timetable(
    school: School,
    block: TimetableQueryBuilder.() -> Unit = {}
): List<Timetable> =
    timetable(school.office, school.code, school.type, block)

private suspend fun KeisClient.fetchTimetable(
    query: TimetableQuery,
    block: HttpRequestBuilder.() -> Unit = {}
): List<Timetable> {
    return requestRows<TimetableDTO>(query.schoolType.endpoint) {
        query.apply(this)
        block()
    }.map { it.toDomain() }
}

private suspend fun KeisClient.executeTimetable(
    query: TimetableQuery,
    block: HttpRequestBuilder.() -> Unit = {}
): List<Timetable> {
    val result = fetchTimetable(query, block)
    return if (query.fillMissing) result.fillMissing(query.maxPeriod) else result
}

private fun List<Timetable>.fillMissing(maxPeriod: Int): List<Timetable> {
    if (isEmpty()) return emptyList()

    return this.groupBy { it.date }
        .flatMap { (_, dayList) ->
            val periodMap = dayList.associateBy { it.period }
            val template = dayList.first()
            buildList {
                for (period in 1..maxPeriod) {
                    add(periodMap[period] ?: template.toEmpty(period))
                }
            }
        }
}

private fun Timetable.toEmpty(period: Int): Timetable {
    return copy(
        period = period,
        order = null,
        major = null,
        classroom = null,
        content = null
    )
}
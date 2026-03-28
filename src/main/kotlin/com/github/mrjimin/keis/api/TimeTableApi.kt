package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.TimetableQueryBuilder
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.internal.*
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable
import com.github.mrjimin.keis.model.dto.TimetableDto
import com.github.mrjimin.keis.model.query.TimetableQuery
import io.ktor.client.request.*
import java.time.LocalDate

private suspend fun KeisClient.fetchTimetable(
    officeCode: String,
    schoolCode: String,
    schoolType: SchoolType,
    from: LocalDate,
    to: LocalDate,
    grade: Int?,
    classNumber: Int?,
    major: String?
): List<Timetable> {
    return requestRows<TimetableDto>(schoolType.endpoint) {
        parameter("ATPT_OFCDC_SC_CODE", officeCode)
        parameter("SD_SCHUL_CODE", schoolCode)
        parameter("TI_FROM_YMD", dateFormat.format(from))
        parameter("TI_TO_YMD", dateFormat.format(to))
        grade?.let { parameter("GRADE", it) }
        classNumber?.let { parameter("CLASS_NM", it) }
        major?.let { parameter("DDDEP_NM", it) }
    }.map { it.toDomain() }
}

suspend fun KeisClient.timetable(
    school: School,
    block: TimetableQueryBuilder.() -> Unit = {}
): List<Timetable> {
    return timetable(school.office.code, school.code, school.type, block)
}

suspend fun KeisClient.timetable(
    officeCode: String,
    schoolCode: String,
    schoolType: SchoolType,
    block: TimetableQueryBuilder.() -> Unit = {}
): List<Timetable> {
    val query = TimetableQueryBuilder(officeCode, schoolCode, schoolType).apply(block).build()
    return executeTimetable(query)
}

private suspend fun KeisClient.executeTimetable(query: TimetableQuery): List<Timetable> {
    val result = fetchTimetable(
        query.officeCode,
        query.schoolCode,
        query.schoolType,
        query.from,
        query.to,
        query.grade,
        query.classNumber,
        query.major
    )
    return if (query.fillMissing) result.fillMissing(query.maxPeriod) else result
}

private fun List<Timetable>.fillMissing(maxPeriod: Int): List<Timetable> {
    if (isEmpty()) return emptyList()
    return groupBy { it.date }
        .flatMap { (_, dayList) ->
            val map = dayList.associateBy { it.period }
            val template = dayList.first()
            (1..maxPeriod).map { period ->
                map.getOrElse(period) { template.toEmpty(period) }
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
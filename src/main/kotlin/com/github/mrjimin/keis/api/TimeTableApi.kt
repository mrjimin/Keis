package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.internal.*
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable
import com.github.mrjimin.keis.model.dto.TimetableDto
import io.ktor.client.request.*
import java.time.LocalDate

private suspend fun KeisClient.fetchTimetable(
    schoolType: SchoolType,
    block: HttpRequestBuilder.() -> Unit
): List<Timetable> {
    return requestRows<TimetableDto>(schoolType.endpoint, block)
        .map { it.toDomain() }
}

suspend fun KeisClient.timetable(
    officeCode: String,
    schoolCode: String,
    schoolType: SchoolType,
    from: LocalDate = startOfWeek(),
    to: LocalDate = endOfWeek(),
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null,
    fillMissing: Boolean = false,
    maxPeriod: Int = schoolType.defaultMaxPeriod
): List<Timetable> {

    val result = fetchTimetable(schoolType) {
        timetableParams(
            officeCode,
            schoolCode,
            from,
            to,
            grade,
            classNumber,
            major
        )
    }

    return if (fillMissing) result.fillMissing(maxPeriod) else result
}

suspend fun KeisClient.timetableBySchool(
    school: School,
    from: LocalDate = startOfWeek(),
    to: LocalDate = endOfWeek(),
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null,
    fillMissing: Boolean = false,
    maxPeriod: Int = school.type.defaultMaxPeriod
): List<Timetable> {

    val result = fetchTimetable(school.type) {
        timetableParams(
            school.office.code,
            school.code,
            from,
            to,
            grade,
            classNumber,
            major
        )
    }

    return if (fillMissing) result.fillMissing(maxPeriod) else result
}

private fun List<Timetable>.fillMissing(maxPeriod: Int): List<Timetable> {
    if (isEmpty()) return emptyList()

    return groupBy { it.date }
        .flatMap { (_, dayList) ->
            val map = dayList.associateBy { it.period }
            val template = dayList.first()

            (1..maxPeriod).map { period ->
                map[period] ?: template.toEmpty(period)
            }
        }
}

private fun HttpRequestBuilder.timetableParams(
    officeCode: String,
    schoolCode: String,
    from: LocalDate,
    to: LocalDate,
    grade: Int?,
    classNumber: Int?,
    major: String?
) {
    parameter("ATPT_OFCDC_SC_CODE", officeCode)
    parameter("SD_SCHUL_CODE", schoolCode)
    parameter("TI_FROM_YMD", dateFormat.format(from))
    parameter("TI_TO_YMD", dateFormat.format(to))

    grade?.let { parameter("GRADE", it) }
    classNumber?.let { parameter("CLASS_NM", it) }
    major?.let { parameter("DDDEP_NM", it) }
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
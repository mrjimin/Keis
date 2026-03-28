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
    officeCode: String,
    schoolCode: String,
    schoolType: SchoolType,
    from: LocalDate,
    to: LocalDate,
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null,
    fillMissing: Boolean = false
): List<Timetable> {

    val timetables = requestRows<TimetableDto>(schoolType.endpoint) {
        parameter("ATPT_OFCDC_SC_CODE", officeCode)
        parameter("SD_SCHUL_CODE", schoolCode)
        parameter("TI_FROM_YMD", dateFormat.format(from))
        parameter("TI_TO_YMD", dateFormat.format(to))
        grade?.let { parameter("GRADE", it) }
        classNumber?.let { parameter("CLASS_NM", it) }
        major?.let { parameter("DDDEP_NM", it) }
    }.map { it.toDomain() }

    if (!fillMissing) return timetables

    val maxPeriod = timetables.maxOfOrNull { it.period } ?: return timetables
    return (1..maxPeriod).mapNotNull { period ->
        val found = timetables.find { it.period == period }

        (found ?: timetables.lastOrNull()?.copy(period = period))?.copy(
            order = found?.order,
            major = found?.major,
            content = found?.content
        )
    }
}

//private suspend fun KeisClient.fetchTimetable(
//    officeCode: String,
//    schoolCode: String,
//    schoolType: SchoolType,
//    from: LocalDate,
//    to: LocalDate,
//    grade: Int? = null,
//    classNumber: Int? = null,
//    major: String? = null
//): List<Timetable> {
//    val dto = requestRows<TimetableDto>(schoolType.endpoint) {
//        parameter("ATPT_OFCDC_SC_CODE", officeCode)
//        parameter("SD_SCHUL_CODE", schoolCode)
//        parameter("TI_FROM_YMD", dateFormat.format(from))
//        parameter("TI_TO_YMD", dateFormat.format(to))
//
//        grade?.let { parameter("GRADE", it) }
//        classNumber?.let { parameter("CLASS_NM", it) }
//        major?.let { parameter("DDDEP_NM", it) }
//    }
//
//    return dto.map { it.toDomain() }
//}

suspend fun KeisClient.timetable(
    officeCode: String,
    schoolCode: String,
    schoolType: SchoolType,
    from: LocalDate = startOfWeek(),
    to: LocalDate = endOfWeek(),
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null,
    fillMissing: Boolean = false
): List<Timetable> {
    return fetchTimetable(officeCode, schoolCode, schoolType, from, to, grade, classNumber, major, fillMissing)
}

suspend fun KeisClient.timetableBySchool(
    school: School,
    from: LocalDate = startOfWeek(),
    to: LocalDate = endOfWeek(),
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null,
    fillMissing: Boolean = false
): List<Timetable> {
    return fetchTimetable(
        school.office.code,
        school.code,
        school.type,
        from,
        to,
        grade,
        classNumber,
        major,
        fillMissing
    )
}
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
    major: String? = null
): List<Timetable> {
    val dto = requestRows<TimetableDto>(schoolType.endpoint) {
        parameter("ATPT_OFCDC_SC_CODE", officeCode)
        parameter("SD_SCHUL_CODE", schoolCode)
        parameter("TI_FROM_YMD", dateFormat.format(from))
        parameter("TI_TO_YMD", dateFormat.format(to))

        grade?.let { parameter("GRADE", it) }
        classNumber?.let { parameter("CLASS_NM", it) }
        major?.let { parameter("DDDEP_NM", it) }
    }

    return dto.map { it.toDomain() }
}

suspend fun KeisClient.timetable(
    officeCode: String,
    schoolCode: String,
    schoolType: SchoolType,
    from: LocalDate = startOfWeek(),
    to: LocalDate = endOfWeek(),
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null
): List<Timetable> {
    return fetchTimetable(officeCode, schoolCode, schoolType, from, to, grade, classNumber, major)
}

suspend fun KeisClient.timetableBySchool(
    school: School,
    from: LocalDate = startOfWeek(),
    to: LocalDate = endOfWeek(),
    grade: Int? = null,
    classNumber: Int? = null,
    major: String? = null
): List<Timetable> {
    return fetchTimetable(
        officeCode = school.office.code,
        schoolCode = school.code,
        schoolType = school.type,
        from = from,
        to = to,
        grade = grade,
        classNumber = classNumber,
        major = major
    )
}
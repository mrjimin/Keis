package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.model.dto.SchoolDto
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.model.domain.School
import io.ktor.client.request.*

private suspend fun KeisClient.fetchSchool(
    builder: HttpRequestBuilder.() -> Unit
): List<SchoolDto> {
    return requestRows("schoolInfo", builder)
}

suspend fun KeisClient.school(schoolName: String): List<School> {
    return fetchSchool {
        parameter("SCHUL_NM", schoolName)
    }.map { it.toDomain() }
}

suspend fun KeisClient.schoolOne(schoolName: String): School? {
    return school(schoolName).firstOrNull()
}

suspend fun KeisClient.schoolByCode(educationOffice: EducationOffice, schoolCode: Int): School? {
    return fetchSchool {
        parameter("ATPT_OFCDC_SC_CODE", educationOffice.code)
        parameter("SD_SCHUL_CODE", schoolCode)
    }.map { it.toDomain() }.firstOrNull()
}

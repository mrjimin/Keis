package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.context.SchoolContext
import com.github.mrjimin.keis.model.dto.SchoolDTO
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.model.domain.School
import io.ktor.client.request.*

private suspend fun KeisClient.fetchSchool(
    block: HttpRequestBuilder.() -> Unit
): List<School> {
    return requestRows<SchoolDTO>("schoolInfo", block)
        .map { it.toDomain() }
}

suspend fun KeisClient.schools(schoolName: String): List<School> {
    return fetchSchool {
        parameter("SCHUL_NM", schoolName)
    }
}

suspend fun KeisClient.school(schoolName: String): School? {
    return schools(schoolName).firstOrNull()
}

suspend fun KeisClient.schoolByCode(educationOffice: EducationOffice, schoolCode: Int): School? {
    return fetchSchool {
        parameter("ATPT_OFCDC_SC_CODE", educationOffice.code)
        parameter("SD_SCHUL_CODE", schoolCode)
    }.firstOrNull()
}

suspend fun KeisClient.schoolContext(name: String): SchoolContext? {
    val school = school(name) ?: return null
    return SchoolContext(this, school)
}

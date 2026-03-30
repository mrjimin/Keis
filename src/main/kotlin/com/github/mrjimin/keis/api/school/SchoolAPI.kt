package com.github.mrjimin.keis.api.school

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.dto.SchoolDTO
import io.ktor.client.request.*

suspend fun KeisClient.schools(name: String): List<School> =
    schools { name(name) }

suspend fun KeisClient.school(name: String): School? =
    school { name(name) }

suspend fun KeisClient.schools(
    office: EducationOffice,
    name: String
): List<School> =
    schools {
        office(office)
        name(name)
    }

suspend fun KeisClient.school(
    office: EducationOffice,
    schoolCode: Int
): School? =
    school {
        office(office)
        code(schoolCode)
    }

suspend fun KeisClient.schools(
    block: SchoolQueryBuilder.() -> Unit = {}
): List<School> {
    val query = SchoolQueryBuilder()
        .apply(block)
        .build()

    return fetchSchool(query)
}

suspend fun KeisClient.school(
    block: SchoolQueryBuilder.() -> Unit = {}
): School? =
    schools(block).firstOrNull()

suspend fun KeisClient.schoolContext(
    name: String
): SchoolContext? =
    schoolContext { name(name) }

suspend fun KeisClient.schoolContext(
    block: SchoolQueryBuilder.() -> Unit = {}
): SchoolContext? {
    val school = school(block) ?: return null
    return SchoolContext(this, school)
}

private suspend fun KeisClient.fetchSchool(
    query: SchoolQuery,
    block: HttpRequestBuilder.() -> Unit = {}
): List<School> {
    return requestRows<SchoolDTO>("schoolInfo") {
        query.apply(this)
        block()
    }.map { it.toDomain() }
}

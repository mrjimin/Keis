package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.enums.EducationOffice
import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.dto.SchoolDTO
import com.github.mrjimin.keis.core.model.query.single

suspend fun KeisClient.schools(
    block: SchoolQueryBuilder.() -> Unit = {}
): List<School> =
    requestSchool(schoolQuery(block))

suspend fun KeisClient.school(
    block: SchoolQueryBuilder.() -> Unit = {}
): School? =
    schools {
        block()
        single()
    }.firstOrNull()

suspend fun KeisClient.school(
    office: EducationOffice,
    code: Int
): School? =
    schools {
        school(office, code)
        single()
    }.firstOrNull()

suspend fun KeisClient.school(name: String): School? =
    schools {
        name(name)
        single()
    }.firstOrNull()

suspend fun KeisClient.schoolContext(
    block: SchoolQueryBuilder.() -> Unit = {}
): SchoolContext? =
    school(block)?.let { SchoolContext(this, it) }

suspend fun KeisClient.schoolContext(name: String): SchoolContext? =
    schoolContext { name(name) }

private suspend fun KeisClient.requestSchool(query: SchoolQuery): List<School> =
    requestRows<SchoolDTO>("schoolInfo", query.toMap())
        .map { it.toDomain() }

inline fun schoolQuery(block: SchoolQueryBuilder.() -> Unit): SchoolQuery =
    SchoolQueryBuilder().apply(block).build()
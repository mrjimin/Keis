package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.endpoint.SchoolEndpoint

suspend fun KeisClient.schools(
    block: SchoolQuery.() -> Unit = {}
): List<School> = fetch(
    SchoolEndpoint,
    ::SchoolQuery,
    block
)

suspend fun KeisClient.school(
    name: String
): School? = schools {
    schoolName(name)
}.firstOrNull()

suspend fun KeisClient.school(
    office: EducationOffice,
    code: Int
): School? = schools {
    school(office, code)
}.firstOrNull()

suspend fun KeisClient.schoolContext(
    block: SchoolQuery.() -> Unit = {}
): SchoolContext? = schools(block).firstOrNull()?.asContext(this)

suspend fun KeisClient.schoolContext(
    name: String
): SchoolContext? = school(name)?.asContext(this)
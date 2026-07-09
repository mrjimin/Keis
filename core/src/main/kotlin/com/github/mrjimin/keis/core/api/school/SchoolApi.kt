package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.endpoint.SchoolEndpoint

/**
 * 학교를 조회합니다.
 *
 * [SchoolQuery] DSL로 조회 조건을 지정할 수 있습니다.
 */
fun KeisClient.schools(
    block: SchoolQuery.() -> Unit = {}
): List<School> = fetch(
    SchoolEndpoint,
    ::SchoolQuery,
    block
)

/**
 * 학교 이름으로 학교를 조회합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 반환합니다.
 *
 * @param name 학교 이름
 */
fun KeisClient.school(
    name: String
): School? = schools {
    schoolName(name)
}.firstOrNull()

/**
 * 교육청과 학교 코드로 학교를 조회합니다.
 *
 * @param office 교육청
 * @param code 학교 코드
 */
fun KeisClient.school(
    office: EducationOffice,
    code: Int
): School? = schools {
    school(office, code)
}.firstOrNull()

/**
 * 학교를 조회하고 [SchoolContext]를 생성합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 사용합니다.
 */
fun KeisClient.schoolContext(
    block: SchoolQuery.() -> Unit = {}
): SchoolContext? = schools(block).firstOrNull()?.asContext(this)

/**
 * 학교 이름으로 [SchoolContext]를 생성합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 사용합니다.
 *
 * @param name 학교 이름
 */
fun KeisClient.schoolContext(
    name: String
): SchoolContext? = school(name)?.asContext(this)

/**
 * 학교를 조회합니다.
 *
 * [SchoolQuery] DSL로 조회 조건을 지정할 수 있습니다.
 */
suspend fun KeisClient.suspendingSchools(
    block: SchoolQuery.() -> Unit = {}
): List<School> = suspendingFetch(
    SchoolEndpoint,
    ::SchoolQuery,
    block
)

/**
 * 학교 이름으로 학교를 조회합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 반환합니다.
 *
 * @param name 학교 이름
 */
suspend fun KeisClient.suspendingSchool(
    name: String
): School? = suspendingSchools {
    schoolName(name)
}.firstOrNull()

/**
 * 교육청과 학교 코드로 학교를 조회합니다.
 *
 * @param office 교육청
 * @param code 학교 코드
 */
suspend fun KeisClient.suspendingSchool(
    office: EducationOffice,
    code: Int
): School? = suspendingSchools {
    school(office, code)
}.firstOrNull()

/**
 * 학교를 조회하고 [SchoolContext]를 생성합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 사용합니다.
 */
suspend fun KeisClient.suspendingSchoolContext(
    block: SchoolQuery.() -> Unit = {}
): SchoolContext? = suspendingSchools(block).firstOrNull()?.asContext(this)

/**
 * 학교 이름으로 [SchoolContext]를 생성합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 사용합니다.
 *
 * @param name 학교 이름
 */
suspend fun KeisClient.suspendingSchoolContext(
    name: String
): SchoolContext? = suspendingSchool(name)?.asContext(this)
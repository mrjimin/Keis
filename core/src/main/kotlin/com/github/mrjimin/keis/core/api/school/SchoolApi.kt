package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.endpoint.SchoolEndpoint

/**
 * 학교 정보를 조회합니다.
 *
 * [SchoolQuery]를 통해 학교명, 교육청, 학교 종류 등의 조회 조건을 지정할 수 있습니다.
 *
 * @param block 조회 조건을 설정하는 DSL
 * @return 조회된 학교 목록
 */
fun KeisClient.schools(
    block: SchoolQuery.() -> Unit = {}
): List<School> = fetch(
    SchoolEndpoint,
    ::SchoolQuery,
    block
)

/**
 * 학교명을 기준으로 학교를 조회합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 반환합니다.
 *
 * @param name 학교명
 * @return 조회된 학교 또는 없으면 `null`
 */
fun KeisClient.school(
    name: String
): School? = schools {
    schoolName(name)
}.firstOrNull()

/**
 * 교육청과 학교 코드를 기준으로 학교를 조회합니다.
 *
 * @param office 교육청
 * @param code 학교 코드
 * @return 조회된 학교 또는 없으면 `null`
 */
fun KeisClient.school(
    office: EducationOffice,
    code: Int
): School? = schools {
    school(office, code)
}.firstOrNull()

/**
 * 학교 정보를 조회한 후 [SchoolContext]를 생성합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 사용합니다.
 *
 * @param block 조회 조건을 설정하는 DSL
 * @return 생성된 [SchoolContext] 또는 조회 결과가 없으면 `null`
 */
fun KeisClient.schoolContext(
    block: SchoolQuery.() -> Unit = {}
): SchoolContext? = schools(block).firstOrNull()?.asContext(this)

/**
 * 학교명을 기준으로 [SchoolContext]를 생성합니다.
 *
 * 조회 결과가 여러 개인 경우 첫 번째 학교를 사용합니다.
 *
 * @param name 학교명
 * @return 생성된 [SchoolContext] 또는 조회 결과가 없으면 `null`
 */
fun KeisClient.schoolContext(
    name: String
): SchoolContext? = school(name)?.asContext(this)
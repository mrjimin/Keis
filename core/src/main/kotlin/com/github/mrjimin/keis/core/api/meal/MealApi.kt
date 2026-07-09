package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.Meal
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.endpoint.MealEndpoint

/**
 * 급식 정보를 조회합니다.
 *
 * [MealQuery]를 통해 학교, 날짜, 기간 등의 조회 조건을 지정할 수 있습니다.
 *
 * 기본적으로 조건을 지정하지 않으면 API의 기본 조회 조건이 적용됩니다.
 *
 * @param block 조회 조건을 설정하는 DSL
 * @return 조회된 급식 정보 목록
 */
fun KeisClient.meals(
    block: MealQuery.() -> Unit = {}
): List<Meal> = fetch(
    MealEndpoint,
    ::MealQuery,
    block
)

/**
 * 지정한 학교의 급식 정보를 조회합니다.
 *
 * [school]이 조회 조건에 자동으로 설정되며,
 * [block]에서 날짜, 기간 등의 추가 조건을 함께 지정할 수 있습니다.
 *
 * @param school 조회할 학교
 * @param block 추가 조회 조건을 설정하는 DSL
 * @return 조회된 급식 정보 목록
 */
fun KeisClient.meals(
    school: School,
    block: MealQuery.() -> Unit = {}
): List<Meal> = meals {
    school(school)
    block()
}
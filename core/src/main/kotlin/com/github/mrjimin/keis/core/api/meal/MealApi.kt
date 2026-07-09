package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.Meal
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.endpoint.MealEndpoint

/**
 * 급식 정보를 조회합니다.
 *
 * [MealQuery] DSL로 조회 조건을 지정할 수 있습니다.
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
 * @param school 조회할 학교
 * @param block 추가 조회 조건
 */
fun KeisClient.meals(
    school: School,
    block: MealQuery.() -> Unit = {}
): List<Meal> = meals {
    school(school)
    block()
}

/**
 * 급식 정보를 조회합니다.
 *
 * [MealQuery] DSL로 조회 조건을 지정할 수 있습니다.
 */
suspend fun KeisClient.suspendingMeals(
    block: MealQuery.() -> Unit = {}
): List<Meal> = suspendingFetch(
    MealEndpoint,
    ::MealQuery,
    block
)

/**
 * 지정한 학교의 급식 정보를 조회합니다.
 *
 * @param school 조회할 학교
 * @param block 추가 조회 조건
 */
suspend fun KeisClient.suspendingMeals(
    school: School,
    block: MealQuery.() -> Unit = {}
): List<Meal> = suspendingMeals {
    school(school)
    block()
}
package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.domain.enums.MealType
import com.github.mrjimin.keis.core.query.DateQuery

/**
 * 급식 정보를 조회하기 위한 조건을 설정하는 DSL입니다.
 *
 * 학교, 조회 날짜 또는 기간, 급식 종류 등의 조건을 지정할 수 있습니다.
 *
 * ```
 * client.meals {
 *     school(school)
 *     today()
 *     lunch()
 * }
 * ```
 */
class MealQuery : DateQuery(
    "MLSV_YMD",
    "MLSV_FROM_YMD",
    "MLSV_TO_YMD"
) {

    /**
     * 조회할 급식 종류를 설정합니다.
     *
     * @param type 급식 종류
     */
    fun type(type: MealType) {
        put("MMEAL_SC_CODE", type.code)
    }

    /**
     * 조식을 조회합니다.
     */
    fun breakfast() {
        type(MealType.BREAKFAST)
    }

    /**
     * 중식을 조회합니다.
     */
    fun lunch() {
        type(MealType.LUNCH)
    }

    /**
     * 석식을 조회합니다.
     */
    fun dinner() {
        type(MealType.DINNER)
    }
}
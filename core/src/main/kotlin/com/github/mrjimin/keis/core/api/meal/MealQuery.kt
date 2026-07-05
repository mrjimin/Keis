package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.domain.enums.MealType
import com.github.mrjimin.keis.core.query.DateQuery

class MealQuery : DateQuery(
    "MLSV_YMD",
    "MLSV_FROM_YMD",
    "MLSV_TO_YMD"
) {
    fun type(type: MealType) {
        put("MMEAL_SC_CODE", type.code)
    }

    fun breakfast() {
        type(MealType.BREAKFAST)
    }

    fun lunch() {
        type(MealType.LUNCH)
    }

    fun dinner() {
        type(MealType.DINNER)
    }

}
package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.enums.MealType
import com.github.mrjimin.keis.core.model.query.QueryBuilder
import com.github.mrjimin.keis.core.model.query.context.DateRangeQuery

class MealQueryBuilder : QueryBuilder<MealQuery>(), DateRangeQuery {
    override val fromKey: String = "MLSV_FROM_YMD"
    override val toKey: String = "MLSV_TO_YMD"

    fun type(mealType: MealType = MealType.ALL) = apply {
        if (mealType != MealType.ALL) {
            put("MMEAL_SC_CODE", mealType.code)
        }
    }

    override fun create(params: Map<String, String>): MealQuery =
        MealQuery(params)
}
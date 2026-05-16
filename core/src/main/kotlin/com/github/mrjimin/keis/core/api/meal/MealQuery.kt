package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.internal.query.Query

data class MealQuery(
    private val params: Map<String, String>
) : Query {
    override fun toMap(): Map<String, String> = params
}
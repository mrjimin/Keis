package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.model.query.Query

data class SchoolQuery(
    private val params: Map<String, String>
) : Query {
    override fun toMap(): Map<String, String> = params
}
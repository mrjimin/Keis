package com.github.mrjimin.keis.core.model.query

import com.github.mrjimin.keis.core.model.query.context.PagingQuery
import com.github.mrjimin.keis.core.model.query.context.QueryContext
import com.github.mrjimin.keis.core.model.query.context.SchoolQuery
import com.github.mrjimin.keis.core.model.query.context.StatsOptionQuery

abstract class QueryBuilder<Q : Query>: QueryContext, PagingQuery, StatsOptionQuery, SchoolQuery {
    protected val params = mutableMapOf<String, String>()

    override fun put(key: String, value: Any?) {
        value?.let { params[key] = it.toString() }
    }

    fun build(): Q = create(params.toMap())

    protected abstract fun create(params: Map<String, String>): Q
}

internal fun QueryBuilder<*>.single() = apply {
    size(1)
}
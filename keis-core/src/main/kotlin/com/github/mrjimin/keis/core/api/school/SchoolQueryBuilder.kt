package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.model.query.QueryBuilder

class SchoolQueryBuilder : QueryBuilder<SchoolQuery>() {

    override fun create(params: Map<String, String>): SchoolQuery =
        SchoolQuery(params)

}
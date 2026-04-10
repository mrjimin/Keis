package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.internal.query.QueryBuilder
import com.github.mrjimin.keis.core.internal.query.context.DateRangeQuery

class TimetableQueryBuilder(
    val school: School
) : QueryBuilder<TimetableQuery>(), DateRangeQuery {
    override val fromKey: String = "TI_FROM_YMD"
    override val toKey: String = "TI_TO_YMD"

    private var fillMissing: Boolean = false
    private var maxPeriod: Int? = null

    fun grade(grade: Int) = apply {
        put("GRADE", grade)
    }

    fun classNumber(classNumber: Int) = apply {
        put("CLASS_NM", classNumber)
    }

    fun major(major: String) = apply {
        put("DDDEP_NM", major)
    }

    fun fillMissing(enable: Boolean = true) = apply {
        fillMissing = enable
    }

    fun maxPeriod(value: Int) = apply {
        require(value > 0) { "교시 수는 1 이상이어야 합니다." }
        maxPeriod = value
    }

    override fun create(params: Map<String, String>): TimetableQuery {
        val finalMaxPeriod = maxPeriod ?: school.type.defaultMaxPeriod

        return TimetableQuery(
            school,
            fillMissing,
            finalMaxPeriod,
            params
        )
    }
}
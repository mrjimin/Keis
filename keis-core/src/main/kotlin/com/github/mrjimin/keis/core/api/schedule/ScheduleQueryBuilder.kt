package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.enums.DayNightCourse
import com.github.mrjimin.keis.core.enums.SchoolCourse
import com.github.mrjimin.keis.core.internal.query.QueryBuilder
import com.github.mrjimin.keis.core.internal.query.context.DateRangeQuery

class ScheduleQueryBuilder : QueryBuilder<ScheduleQuery>(), DateRangeQuery {
    override val fromKey: String = "AA_FROM_YMD"
    override val toKey: String = "AA_TO_YMD"

    fun dayNightCourse(dayNightCourse: DayNightCourse) = apply {
        put("DGHT_CRSE_SC_NM", dayNightCourse.value)
    }

    fun schoolCourse(schoolCourse: SchoolCourse) = apply {
        put("SCHUL_CRSE_SC_NM", schoolCourse.value)
    }

    override fun create(params: Map<String, String>): ScheduleQuery =
        ScheduleQuery(params)

}
package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.internal.query.Query
import com.github.mrjimin.keis.core.model.domain.School

data class TimetableQuery(
    val school: School,
    val fillMissing: Boolean = false,
    val maxPeriod: Int,
    private val params: Map<String, String>
) : Query {
    override fun toMap(): Map<String, String> = params
}
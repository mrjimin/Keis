package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.query.Query

data class TimetableQuery(
    val school: School,
    val fillMissing: Boolean = false,
    val maxPeriod: Int,
    private val params: Map<String, String>
) : Query {
    override fun toMap(): Map<String, String> = params
}
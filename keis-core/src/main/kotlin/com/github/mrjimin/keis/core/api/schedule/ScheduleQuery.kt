package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.internal.query.Query

data class ScheduleQuery(
    private val params: Map<String, String>
) : Query {
    override fun toMap(): Map<String, String> = params
}
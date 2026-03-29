package com.github.mrjimin.keis.api.context

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.builder.TimetableQueryBuilder
import com.github.mrjimin.keis.api.timetable
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable

class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    suspend fun timetable(
        block: TimetableQueryBuilder.() -> Unit = {}
    ): List<Timetable> {
        return client.timetable(school, block)
    }

}

fun School.asContext(client: KeisClient): SchoolContext {
    return SchoolContext(client, this)
}
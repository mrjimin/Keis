package com.github.mrjimin.keis.api.context

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.TimetableQueryBuilder
import com.github.mrjimin.keis.api.*
import com.github.mrjimin.keis.internal.*
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable
import java.time.LocalDate

class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    suspend fun timetable(
        block: TimetableQueryBuilder.() -> Unit = {}
    ): List<Timetable> {
        return client.timetable(school, block)
    }

    suspend fun timetableOf(
        from: LocalDate = startOfWeek(),
        to: LocalDate = endOfWeek(),
        grade: Int? = null,
        classNumber: Int? = null,
        major: String? = null,
        fillMissing: Boolean = false,
        maxPeriod: Int = school.type.defaultMaxPeriod
    ): List<Timetable> {
        return client.timetableOf(
            school,
            from,
            to,
            grade,
            classNumber,
            major,
            fillMissing,
            maxPeriod
        )
    }
}

fun School.asContext(client: KeisClient): SchoolContext {
    return SchoolContext(client, this)
}
package com.github.mrjimin.keis.api.context

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.TimetableQueryBuilder
import com.github.mrjimin.keis.api.*
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable
import java.time.LocalDate

class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    suspend fun timetable(block: TimetableQueryBuilder.() -> Unit = {}): List<Timetable> {
        return client.timetable(school, block)
    }

    suspend fun timetable(
        from: LocalDate = LocalDate.now(),
        to: LocalDate = LocalDate.now(),
        grade: Int? = null,
        classNumber: Int? = null,
        fillMissing: Boolean = false
    ): List<Timetable> {
        return client.timetable(
            school,
            from = from,
            to = to,
            grade = grade,
            classNumber = classNumber,
            fillMissing = fillMissing
        )
    }
}

fun School.toContext(client: KeisClient): SchoolContext {
    return SchoolContext(client, this)
}
package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.domain.Timetable
import com.github.mrjimin.keis.core.model.dto.TimetableDTO
import com.github.mrjimin.keis.core.model.query.single

suspend fun KeisClient.timetables(
    school: School,
    block: TimetableQueryBuilder.() -> Unit = {}
): List<Timetable> =
    requestTimetable(
        timetableQuery(school) {
            block()
        }
    )

suspend fun KeisClient.timetable(
    school: School,
    block: TimetableQueryBuilder.() -> Unit = {}
): Timetable? =
    timetables(school) {
        block()
        single()
    }.firstOrNull()

private suspend fun KeisClient.requestTimetable(query: TimetableQuery): List<Timetable> =
    requestRows<TimetableDTO>(query.school.type.endpoint, query.toMap())
        .map { it.toDomain() }
        .applyFill(query)

private fun List<Timetable>.applyFill(query: TimetableQuery): List<Timetable> =
    if (query.fillMissing) fillMissing(query.maxPeriod) else this

private fun List<Timetable>.fillMissing(maxPeriod: Int): List<Timetable> =
    groupBy(Timetable::date)
        .flatMap { (_, dayList) ->
            val template = dayList.firstOrNull() ?: return@flatMap emptyList()
            val periodMap = dayList.associateBy(Timetable::period)

            (1..maxPeriod).map { period ->
                periodMap[period] ?: template.toEmpty(period)
            }
        }

private fun Timetable.toEmpty(period: Int): Timetable = copy(
    period = period,
    order = null,
    major = null,
    classroom = null,
    content = null
)

inline fun timetableQuery(
    school: School,
    block: TimetableQueryBuilder.() -> Unit
): TimetableQuery =
    TimetableQueryBuilder(school).apply(block).build()

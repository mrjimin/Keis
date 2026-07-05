package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.Timetable
import com.github.mrjimin.keis.core.domain.enums.SchoolType
import com.github.mrjimin.keis.core.endpoint.TimetableEndpoint

suspend fun KeisClient.timetables(
    schoolType: SchoolType,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> {

    val rawList = fetch(
        TimetableEndpoint(schoolType),
        {
            TimetableQuery().apply {
                maxPeriod(schoolType.defaultMaxPeriod)
            }
        },
        block
    )

    val options = TimetableQuery().apply(block)

    return if (options.fill) {
        rawList.fill(options.maxPeriod)
    } else {
        rawList
    }
}

suspend fun KeisClient.timetables(
    school: School,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> = timetables(school.type) {
    school(school)
    block()
}

private fun List<Timetable>.fill(maxPeriod: Int): List<Timetable> =
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
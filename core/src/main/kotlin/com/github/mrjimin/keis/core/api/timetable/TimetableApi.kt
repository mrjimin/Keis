package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.Timetable
import com.github.mrjimin.keis.core.domain.enums.SchoolType
import com.github.mrjimin.keis.core.endpoint.TimetableEndpoint

/**
 * 시간표를 조회합니다.
 *
 * [TimetableQuery] DSL로 조회 조건을 지정할 수 있습니다.
 *
 * 학교급에 맞는 시간표 API를 사용하며, 기본 최대 교시 수가 자동으로
 * 설정됩니다. `fill` 옵션을 사용하면 누락된 교시를 빈 시간표로 채웁니다.
 */
fun KeisClient.timetables(
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

/**
 * 지정한 학교의 시간표를 조회합니다.
 *
 * 학교 정보가 조회 조건에 자동으로 설정됩니다.
 *
 * @param school 조회할 학교
 * @param block 추가 조회 조건
 */
fun KeisClient.timetables(
    school: School,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> = timetables(school.type) {
    school(school)
    block()
}

/**
 * 시간표를 조회합니다.
 *
 * [TimetableQuery] DSL로 조회 조건을 지정할 수 있습니다.
 *
 * 학교급에 맞는 시간표 API를 사용하며, 기본 최대 교시 수가 자동으로
 * 설정됩니다. `fill` 옵션을 사용하면 누락된 교시를 빈 시간표로 채웁니다.
 */
suspend fun KeisClient.suspendingTimetables(
    schoolType: SchoolType,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> {

    val rawList = suspendingFetch(
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

/**
 * 지정한 학교의 시간표를 조회합니다.
 *
 * 학교 정보가 조회 조건에 자동으로 설정됩니다.
 *
 * @param school 조회할 학교
 * @param block 추가 조회 조건
 */
suspend fun KeisClient.suspendingTimetables(
    school: School,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> = suspendingTimetables(school.type) {
    school(school)
    block()
}

/**
 * 누락된 교시를 빈 시간표로 채웁니다.
 *
 * @param maxPeriod 하루 최대 교시 수
 */
private fun List<Timetable>.fill(maxPeriod: Int): List<Timetable> =
    groupBy(Timetable::date)
        .flatMap { (_, dayList) ->
            val template = dayList.firstOrNull() ?: return@flatMap emptyList()
            val periodMap = dayList.associateBy(Timetable::period)

            (1..maxPeriod).map { period ->
                periodMap[period] ?: template.toEmpty(period)
            }
        }

/**
 * 빈 시간표를 생성합니다.
 *
 * @param period 교시
 */
private fun Timetable.toEmpty(period: Int): Timetable = copy(
    period = period,
    order = null,
    major = null,
    classroom = null,
    content = null
)
package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.Schedule
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.endpoint.ScheduleEndpoint

/**
 * 학사일정을 조회합니다.
 *
 * [ScheduleQuery] DSL로 조회 조건을 지정할 수 있습니다.
 */
fun KeisClient.schedules(
    block: ScheduleQuery.() -> Unit = {}
): List<Schedule> = fetch(
    ScheduleEndpoint,
    ::ScheduleQuery,
    block
)

/**
 * 지정한 학교의 학사일정을 조회합니다.
 *
 * @param school 조회할 학교
 * @param block 추가 조회 조건
 */
fun KeisClient.schedules(
    school: School,
    block: ScheduleQuery.() -> Unit = {}
): List<Schedule> = schedules {
    school(school)
    block()
}

/**
 * 학사일정을 조회합니다.
 *
 * [ScheduleQuery] DSL로 조회 조건을 지정할 수 있습니다.
 */
suspend fun KeisClient.suspendingSchedules(
    block: ScheduleQuery.() -> Unit = {}
): List<Schedule> = suspendingFetch(
    ScheduleEndpoint,
    ::ScheduleQuery,
    block
)

/**
 * 지정한 학교의 학사일정을 조회합니다.
 *
 * @param school 조회할 학교
 * @param block 추가 조회 조건
 */
suspend fun KeisClient.suspendingSchedules(
    school: School,
    block: ScheduleQuery.() -> Unit = {}
): List<Schedule> = suspendingSchedules {
    school(school)
    block()
}
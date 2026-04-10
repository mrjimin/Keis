package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.model.domain.Schedule
import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.dto.ScheduleDTO
import com.github.mrjimin.keis.core.internal.query.single

suspend fun KeisClient.schedules(
    block: ScheduleQueryBuilder.() -> Unit = {}
): List<Schedule> =
    requestSchedule(scheduleQuery(block))

suspend fun KeisClient.schedule(
    block: ScheduleQueryBuilder.() -> Unit = {}
): Schedule? =
    schedules {
        block()
        single()
    }.firstOrNull()

suspend fun KeisClient.schedules(
    school: School,
    block: ScheduleQueryBuilder.() -> Unit = {}
): List<Schedule> =
    schedules {
        school(school)
        block()
    }

suspend fun KeisClient.schedule(
    school: School,
    block: ScheduleQueryBuilder.() -> Unit = {}
): Schedule? =
    schedules(school) {
        block()
        single()
    }.firstOrNull()

suspend fun KeisClient.schedules(
    school: School
): List<Schedule> =
    schedules {
        school(school)
    }

suspend fun KeisClient.schedule(
    school: School
): Schedule? =
    schedules {
        school(school)
        single()
    }.firstOrNull()


private suspend fun KeisClient.requestSchedule(query: ScheduleQuery): List<Schedule> =
    requestRows<ScheduleDTO>("SchoolSchedule", query.toMap())
        .map { it.toDomain() }

inline fun scheduleQuery(block: ScheduleQueryBuilder.() -> Unit): ScheduleQuery =
    ScheduleQueryBuilder().apply(block).build()
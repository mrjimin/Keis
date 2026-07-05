package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.Schedule
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.endpoint.ScheduleEndpoint

suspend fun KeisClient.schedules(
    block: ScheduleQuery.() -> Unit = {}
): List<Schedule> = fetch(
    ScheduleEndpoint,
    ::ScheduleQuery,
    block
)

suspend fun KeisClient.schedules(
    school: School,
    block: ScheduleQuery.() -> Unit = {}
): List<Schedule> = schedules {
    school(school)
    block()
}

//fun KeisClient.schedulesFlow(
//    school: School,
//    block: ScheduleQueryScope.() -> Unit = {}
//): Flow<Schedule> = flow {
//    fetch(
//        ScheduleEndpoint,
//        ::ScheduleQueryScope
//    ) {
//        school(school)
//        block()
//    }.forEach { emit(it) }
//}
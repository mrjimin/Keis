package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.api.meal.MealQuery
import com.github.mrjimin.keis.core.api.meal.meals
import com.github.mrjimin.keis.core.api.schedule.ScheduleQuery
import com.github.mrjimin.keis.core.api.schedule.schedules
import com.github.mrjimin.keis.core.api.timetable.TimetableQuery
import com.github.mrjimin.keis.core.api.timetable.timetables
import com.github.mrjimin.keis.core.domain.School

class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    suspend fun meals(
        block: MealQuery.() -> Unit = {}
    ) = client.meals(school, block)

    suspend fun schedules(
        block: ScheduleQuery.() -> Unit = {}
    ) = client.schedules(school, block)

    suspend fun timetables(
        block: TimetableQuery.() -> Unit = {}
    ) = client.timetables(school, block)

}

fun School.asContext(client: KeisClient) =
    SchoolContext(client, this)

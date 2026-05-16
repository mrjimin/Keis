package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.api.meal.MealQueryBuilder
import com.github.mrjimin.keis.core.api.meal.meal
import com.github.mrjimin.keis.core.api.meal.meals
import com.github.mrjimin.keis.core.api.schedule.ScheduleQueryBuilder
import com.github.mrjimin.keis.core.api.schedule.schedule
import com.github.mrjimin.keis.core.api.schedule.schedules
import com.github.mrjimin.keis.core.api.timetable.TimetableQueryBuilder
import com.github.mrjimin.keis.core.api.timetable.timetable
import com.github.mrjimin.keis.core.api.timetable.timetables
import com.github.mrjimin.keis.core.model.domain.Meal
import com.github.mrjimin.keis.core.model.domain.Schedule
import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.domain.Timetable

class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    suspend fun meal(
        block: MealQueryBuilder.() -> Unit = {}
    ): Meal? = client.meal(school, block)

    suspend fun meals(
        block: MealQueryBuilder.() -> Unit = {}
    ): List<Meal> = client.meals(school, block)

    suspend fun schedule(
        block: ScheduleQueryBuilder.() -> Unit = {}
    ): Schedule? = client.schedule(school, block)

    suspend fun schedules(
        block: ScheduleQueryBuilder.() -> Unit = {}
    ): List<Schedule> = client.schedules(school, block)

    suspend fun timetable(
        block: TimetableQueryBuilder.() -> Unit = {}
    ): Timetable? = client.timetable(school, block)

    suspend fun timetables(
        block: TimetableQueryBuilder.() -> Unit = {}
    ): List<Timetable> = client.timetables(school, block)

}

fun School.asContext(client: KeisClient): SchoolContext =
    SchoolContext(client, this)
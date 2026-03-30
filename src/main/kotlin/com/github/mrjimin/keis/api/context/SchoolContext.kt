package com.github.mrjimin.keis.api.context

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.builder.MealQueryBuilder
import com.github.mrjimin.keis.api.dsl.builder.ScheduleQueryBuilder
import com.github.mrjimin.keis.api.dsl.builder.TimetableQueryBuilder
import com.github.mrjimin.keis.api.meal
import com.github.mrjimin.keis.api.schedule
import com.github.mrjimin.keis.api.timetable
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.model.domain.Meal
import com.github.mrjimin.keis.model.domain.Schedule
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.domain.Timetable

class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    suspend fun timetable(
        block: TimetableQueryBuilder.() -> Unit = {}
    ): List<Timetable> {
        return client.timetable(school, block)
    }

    suspend fun meal(
        mealType: MealType = MealType.ALL,
        block: MealQueryBuilder.() -> Unit = {}
    ): List<Meal> {
        return client.meal(school, mealType, block)
    }

    suspend fun schedule(
        block: ScheduleQueryBuilder.() -> Unit = {}
    ): List<Schedule> {
        return client.schedule(school, block)
    }

}

fun School.asContext(client: KeisClient): SchoolContext {
    return SchoolContext(client, this)
}
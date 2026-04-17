package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.query.single
import com.github.mrjimin.keis.core.model.domain.Meal
import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.dto.MealDTO

suspend fun KeisClient.meals(
    block: MealQueryBuilder.() -> Unit = {}
): List<Meal> =
    requestMeal(mealQuery(block))

suspend fun KeisClient.meal(
    block: MealQueryBuilder.() -> Unit = {}
): Meal? =
    meals {
        block()
        single()
    }.firstOrNull()

suspend fun KeisClient.meals(
    school: School,
    block: MealQueryBuilder.() -> Unit = {}
): List<Meal> =
    meals {
        school(school)
        block()
    }

suspend fun KeisClient.meal(
    school: School,
    block: MealQueryBuilder.() -> Unit = {}
): Meal? =
    meals(school) {
        block()
        single()
    }.firstOrNull()

suspend fun KeisClient.meals(
    school: School
): List<Meal> =
    meals {
        school(school)
    }

suspend fun KeisClient.meal(
    school: School
): Meal? =
    meals {
        school(school)
        single()
    }.firstOrNull()

private suspend fun KeisClient.requestMeal(query: MealQuery): List<Meal> =
    requestRows<MealDTO>("mealServiceDietInfo", query.toMap())
        .map { it.toDomain() }

inline fun mealQuery(block: MealQueryBuilder.() -> Unit): MealQuery =
    MealQueryBuilder().apply(block).build()
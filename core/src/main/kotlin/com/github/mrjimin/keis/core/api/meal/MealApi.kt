package com.github.mrjimin.keis.core.api.meal

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.Meal
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.endpoint.MealEndpoint

suspend fun KeisClient.meals(
    block: MealQuery.() -> Unit = {}
): List<Meal> = fetch(
    MealEndpoint,
    ::MealQuery,
    block
)

suspend fun KeisClient.meals(
    school: School,
    block: MealQuery.() -> Unit = {}
): List<Meal> = meals {
    school(school)
    block()
}
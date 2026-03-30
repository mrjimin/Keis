package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.builder.MealQueryBuilder
import com.github.mrjimin.keis.api.dsl.query.MealQuery
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.model.domain.Meal
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.dto.MealDTO
import io.ktor.client.request.*

suspend fun KeisClient.meal(
    office: EducationOffice,
    schoolCode: Int,
    mealType: MealType = MealType.ALL
): List<Meal> =
    meal(office, schoolCode, mealType) {}

suspend fun KeisClient.meal(
    office: EducationOffice,
    schoolCode: Int,
    mealType: MealType = MealType.ALL,
    block: MealQueryBuilder.() -> Unit = {}
): List<Meal> {
    val query = MealQueryBuilder(office, schoolCode, mealType)
        .apply(block)
        .build()

    return fetchMeal(query)
}

suspend fun KeisClient.meal(
    school: School,
    mealType: MealType = MealType.ALL,
    block: MealQueryBuilder.() -> Unit = {}
): List<Meal> =
    meal(school.office, school.code, mealType, block)

private suspend fun KeisClient.fetchMeal(
    query: MealQuery,
    block: HttpRequestBuilder.() -> Unit = {}
): List<Meal> {
    return requestRows<MealDTO>("mealServiceDietInfo") {
        query.apply(this)
        block()
    }.map { it.toDomain() }
}
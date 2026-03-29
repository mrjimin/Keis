package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.dsl.builder.MealQueryBuilder
import com.github.mrjimin.keis.api.dsl.query.MealQuery
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.internal.dateFormat
import com.github.mrjimin.keis.model.domain.Meal
import com.github.mrjimin.keis.model.domain.School
import com.github.mrjimin.keis.model.dto.MealDTO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import java.time.LocalDate

private suspend fun KeisClient.fetchMeal(
    office: EducationOffice,
    schoolCode: String,
    mealType: MealType,
    from: LocalDate,
    to: LocalDate,
    block: HttpRequestBuilder.() -> Unit = {}
): List<Meal> {
    return requestRows<MealDTO>("mealServiceDietInfo") {
        parameter("ATPT_OFCDC_SC_CODE", office.code)
        parameter("SD_SCHUL_CODE", schoolCode)
        if (mealType != MealType.ALL) parameter("MMEAL_SC_CODE", mealType.ordinal)
        parameter("MLSV_FROM_YMD", dateFormat.format(from))
        parameter("MLSV_TO_YMD", dateFormat.format(to))
        block()
    }.map { it.toDomain() }
}

suspend fun KeisClient.meal(
    office: EducationOffice,
    schoolCode: String,
    mealType: MealType = MealType.ALL,
    block: MealQueryBuilder.() -> Unit = {}
): List<Meal> {
    val query = MealQueryBuilder(office, schoolCode, mealType)
        .apply(block)
        .build()
    return fetchMeal(query.office, query.schoolCode, query.mealType, query.from, query.to)
}

suspend fun KeisClient.meal(
    school: School,
    mealType: MealType = MealType.ALL,
    block: MealQueryBuilder.() -> Unit = {}
): List<Meal> {
    return meal(school.office, school.code, mealType, block)
}
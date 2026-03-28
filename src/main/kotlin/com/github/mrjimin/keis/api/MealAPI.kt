package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.model.domain.Meal
import com.github.mrjimin.keis.model.dto.MealDTO
import io.ktor.client.request.HttpRequestBuilder

private suspend fun KeisClient.fetchMeal(
    block: HttpRequestBuilder.() -> Unit
): List<Meal> {
    return requestRows<MealDTO>("mealServiceDietInfo", block)
        .map { it.toDomain() }
}
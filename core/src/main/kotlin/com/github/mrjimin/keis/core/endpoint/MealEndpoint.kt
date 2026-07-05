package com.github.mrjimin.keis.core.endpoint

import com.github.mrjimin.keis.core.domain.Meal
import com.github.mrjimin.keis.core.internal.mapper.toDomain
import com.github.mrjimin.keis.core.internal.response.MealResponse

object MealEndpoint : Endpoint<MealResponse, Meal>(
    "mealServiceDietInfo",
    MealResponse.serializer(),
    MealResponse::toDomain
)
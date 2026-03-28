package com.github.mrjimin.keis.model.dto

import com.github.mrjimin.keis.model.domain.Meal

class MealDto {
    fun toDomain(): Meal = Meal()
}
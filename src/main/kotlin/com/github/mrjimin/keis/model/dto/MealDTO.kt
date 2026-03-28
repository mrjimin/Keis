package com.github.mrjimin.keis.model.dto

import com.github.mrjimin.keis.model.domain.Meal

class MealDTO {
    fun toDomain(): Meal = Meal()
}
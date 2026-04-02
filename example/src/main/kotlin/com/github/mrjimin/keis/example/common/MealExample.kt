package com.github.mrjimin.keis.example.common

import com.github.mrjimin.keis.core.api.school.SchoolContext
import com.github.mrjimin.keis.core.enums.MealType
import java.time.LocalDate

suspend fun mealExample(context: SchoolContext) {

    // 급식 조회
    val meals = context.meals()

    // 특정 급식 (점심)
    val lunch = context.meals {
        type(MealType.LUNCH)
    }

    // 오늘 급식
    val todayMeal = context.meals {
        dateRange {
            today()
        }
        // today() 사용가능
    }

    // 특정 날짜 급식
    val specificDate = context.meals {
        dateRange {
            single(LocalDate.of(2026, 4, 2))
        }
    }

    // 날짜 범위 급식
    val rangeMeals = context.meals {
        dateRange {
            range(LocalDate.now(), LocalDate.now().plusDays(3))
        }
    }

    // 이번 주 급식
    val weekMeals = context.meals {
        dateRange {
            thisWeek()
        }
    }

}
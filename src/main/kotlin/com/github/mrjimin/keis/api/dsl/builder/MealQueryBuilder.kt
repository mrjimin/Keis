package com.github.mrjimin.keis.api.dsl.builder

import com.github.mrjimin.keis.api.dsl.KeisDsl
import com.github.mrjimin.keis.api.dsl.query.MealQuery
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.internal.endOfWeek
import com.github.mrjimin.keis.internal.startOfWeek
import java.time.LocalDate

@KeisDsl
class MealQueryBuilder(
    private val office: EducationOffice,
    private val schoolCode: Int,
    private val mealType: MealType = MealType.ALL,
) {
    private var from: LocalDate = startOfWeek()
    private var to: LocalDate = endOfWeek()

    fun date(date: LocalDate) {
        this.from = date
        this.to = date
    }

    fun dateRange(from: LocalDate, to: LocalDate) {
        this.from = from
        this.to = to
    }

    fun today() {
        val now = LocalDate.now()
        this.from = now
        this.to = now
    }

    fun thisWeek() {
        this.from = startOfWeek()
        this.to = endOfWeek()
    }

    fun build(): MealQuery = MealQuery(
        office,
        schoolCode,
        mealType,
        from,
        to
    )
}
package com.github.mrjimin.keis.api.meal

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.internal.dsl.Builder
import com.github.mrjimin.keis.internal.dsl.DateRange
import com.github.mrjimin.keis.internal.dsl.KeisDsl
import com.github.mrjimin.keis.internal.endOfWeek
import com.github.mrjimin.keis.internal.startOfWeek
import java.time.LocalDate

@KeisDsl
class MealQueryBuilder(
    private val office: EducationOffice,
    private val schoolCode: Int,
    private val mealType: MealType = MealType.ALL,
): Builder<MealQuery>, DateRange {

    override var from: LocalDate = startOfWeek()
    override var to: LocalDate = endOfWeek()
    override var pIndex: Int = 1
    override var pSize: Int = 100
    override var stats: Boolean = false

    override fun build(): MealQuery = MealQuery(
        office,
        schoolCode,
        mealType,
        from,
        to,
        pIndex,
        pSize,
        stats
    )
}
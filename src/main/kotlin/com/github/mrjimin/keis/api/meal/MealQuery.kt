package com.github.mrjimin.keis.api.meal

import com.github.mrjimin.keis.internal.dsl.Query
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.internal.toYmd
import io.ktor.client.request.*
import java.time.LocalDate

data class MealQuery(
    val office: EducationOffice,
    val schoolCode: Int,
    val mealType: MealType,
    val from: LocalDate,
    val to: LocalDate,
    override val pIndex: Int,
    override val pSize: Int,
    override val stats: Boolean,
) : Query {

    override fun apply(builder: HttpRequestBuilder) {
        builder.parameter("ATPT_OFCDC_SC_CODE", office.code)
        builder.parameter("SD_SCHUL_CODE", schoolCode)
        builder.parameter("MLSV_FROM_YMD", from.toYmd())
        builder.parameter("MLSV_TO_YMD", to.toYmd())

        if (mealType != MealType.ALL) builder.parameter("MMEAL_SC_CODE", mealType.code)

        builder.applyPaging()
    }
}
package com.github.mrjimin.keis.api.dsl.query

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.internal.toYmd
import io.ktor.client.request.*
import java.time.LocalDate

data class TimetableQuery(
    val office: EducationOffice,
    val schoolCode: Int,
    val schoolType: SchoolType,
    val from: LocalDate,
    val to: LocalDate,
    val grade: Int? = null,
    val classNumber: Int? = null,
    val major: String? = null,
    val fillMissing: Boolean = false,
    val maxPeriod: Int = schoolType.defaultMaxPeriod
): Query {
    override fun apply(builder: HttpRequestBuilder) {
        builder.parameter("ATPT_OFCDC_SC_CODE", office.code)
        builder.parameter("SD_SCHUL_CODE", schoolCode)
        builder.parameter("TI_FROM_YMD", from.toYmd())
        builder.parameter("TI_TO_YMD", to.toYmd())

        grade?.let { builder.parameter("GRADE", it) }
        classNumber?.let { builder.parameter("CLASS_NM", it) }
        major?.let { builder.parameter("DDDEP_NM", it) }
    }
}
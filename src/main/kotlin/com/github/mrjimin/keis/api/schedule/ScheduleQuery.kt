package com.github.mrjimin.keis.api.schedule

import com.github.mrjimin.keis.api.dsl.marker.Query
import com.github.mrjimin.keis.enums.DayNightCourse
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolCourse
import com.github.mrjimin.keis.internal.toYmd
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import java.time.LocalDate

data class ScheduleQuery(
    val office: EducationOffice,
    val schoolCode: Int,
    val dayNightCourse: DayNightCourse?,
    val schoolCourse: SchoolCourse?,
    val from: LocalDate,
    val to: LocalDate,
    override val pIndex: Int,
    override val pSize: Int,
    override val stats: Boolean,
): Query {
    override fun apply(builder: HttpRequestBuilder) {
        builder.parameter("ATPT_OFCDC_SC_CODE", office.code)
        builder.parameter("SD_SCHUL_CODE", schoolCode)
        builder.parameter("AA_FROM_YMD", from.toYmd())
        builder.parameter("AA_TO_YMD", to.toYmd())

        dayNightCourse?.let { builder.parameter("DGHT_CRSE_SC_NM", it.value) }
        schoolCourse?.let { builder.parameter("SCHUL_CRSE_SC_NM", it.value) }

        builder.applyPaging()
    }
}
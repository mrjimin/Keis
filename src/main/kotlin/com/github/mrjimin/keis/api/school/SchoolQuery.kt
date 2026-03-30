package com.github.mrjimin.keis.api.school

import com.github.mrjimin.keis.api.dsl.marker.Query
import com.github.mrjimin.keis.enums.EducationOffice
import io.ktor.client.request.*

data class SchoolQuery(
    val office: EducationOffice? = null,
    val schoolName: String? = null,
    val schoolCode: Int? = null,
    override val pIndex: Int,
    override val pSize: Int,
    override val stats: Boolean
): Query {
    override fun apply(builder: HttpRequestBuilder) {
        office?.let { builder.parameter("ATPT_OFCDC_SC_CODE", it.code) }
        schoolName?.let { builder.parameter("SCHUL_NM", it) }
        schoolCode?.let { builder.parameter("SD_SCHUL_CODE", it) }

        builder.applyPaging()
    }
}
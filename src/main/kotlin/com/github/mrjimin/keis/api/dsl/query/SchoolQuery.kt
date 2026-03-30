package com.github.mrjimin.keis.api.dsl.query

import com.github.mrjimin.keis.enums.EducationOffice

data class SchoolQuery(
    val office: EducationOffice? = null,
    val schoolName: String? = null,
    val schoolCode: Int? = null
)
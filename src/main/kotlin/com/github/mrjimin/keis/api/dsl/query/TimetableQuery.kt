package com.github.mrjimin.keis.api.dsl.query

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolType
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
)
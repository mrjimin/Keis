package com.github.mrjimin.keis.core.domain

import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Timetable(
    val office: EducationOffice,
    val schoolCode: Int,
    val schoolName: String,
    val grade: Int,
    val classNumber: Int,
    val period: Int,
    val year: Int,
    val semester: Int,
    @Contextual
    val date: LocalDate,
    val order: String? = null,
    val major: String? = null,
    val classroom: String? = null,
    val content: String? = null,
    @Contextual
    val loadDateTime: LocalDate
)
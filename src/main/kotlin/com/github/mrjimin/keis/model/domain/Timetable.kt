package com.github.mrjimin.keis.model.domain

import com.github.mrjimin.keis.enums.EducationOffice
import java.time.LocalDate

data class Timetable(
    val office: EducationOffice,
    val schoolCode: String,
    val schoolName: String,
    val grade: Int,
    val classNumber: Int,
    val period: Int,
    val year: Int,
    val semester: Int,
    val date: LocalDate,
    val order: String? = null,
    val major: String? = null,
    val classroom: String? = null,
    val content: String? = null,
)
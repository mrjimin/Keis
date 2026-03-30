package com.github.mrjimin.keis.model.domain

import com.github.mrjimin.keis.enums.EducationOffice
import java.time.LocalDate

data class Schedule(
    val office: EducationOffice,
    val schoolCode: Int,
    val schoolName: String,
    val date: LocalDate,
    val eventName: String,
    val eventContent: String?,
    val targetGrades: List<Int>
)

package com.github.mrjimin.keis.core.domain

import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Schedule(
    val office: EducationOffice,
    val schoolCode: Int,
    val schoolName: String,
    @Contextual
    val date: LocalDate,
    val eventName: String,
    val eventContent: String?,
    val targetGrades: List<Int>,
    @Contextual
    val loadDateTime: LocalDate
)
package com.github.mrjimin.keis.api.dsl.query

import com.github.mrjimin.keis.enums.DayNightCourse
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolCourse
import java.time.LocalDate

data class ScheduleQuery(
    val office: EducationOffice,
    val schoolCode: Int,
    val dayNightCourse: DayNightCourse?,
    val schoolCourse: SchoolCourse?,
    val from: LocalDate,
    val to: LocalDate,
)
package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.domain.enums.DayNightCourse
import com.github.mrjimin.keis.core.domain.enums.SchoolCourse
import com.github.mrjimin.keis.core.query.DateQuery

class ScheduleQuery : DateQuery(
    "AA_YMD",
    "AA_FROM_YMD",
    "AA_TO_YMD"
) {

    fun dayNightCourse(dayNightCourse: DayNightCourse) = apply {
        put("DGHT_CRSE_SC_NM", dayNightCourse.label)
    }

    fun schoolCourse(schoolCourse: SchoolCourse) = apply {
        put("SCHUL_CRSE_SC_NM", schoolCourse.label)
    }
}
package com.github.mrjimin.keis.api.schedule

import com.github.mrjimin.keis.api.dsl.KeisDsl
import com.github.mrjimin.keis.api.dsl.marker.Builder
import com.github.mrjimin.keis.api.dsl.marker.DateRange
import com.github.mrjimin.keis.enums.DayNightCourse
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolCourse
import com.github.mrjimin.keis.internal.endOfWeek
import com.github.mrjimin.keis.internal.startOfWeek
import java.time.LocalDate

@KeisDsl
class ScheduleQueryBuilder(
    private val office: EducationOffice,
    private val schoolCode: Int
): Builder<ScheduleQuery>, DateRange {

    override var from: LocalDate = startOfWeek()
    override var to: LocalDate = endOfWeek()
    override var pIndex: Int = 1
    override var pSize: Int = 100
    override var stats: Boolean = false

    private var dayNightCourse: DayNightCourse? = null
    private var schoolCourse: SchoolCourse? = null

    fun dayNightCourse(course: DayNightCourse) {
        this.dayNightCourse = course
    }

    fun schoolCourse(course: SchoolCourse) {
        this.schoolCourse = course
    }

    override fun build(): ScheduleQuery = ScheduleQuery(
        office,
        schoolCode,
        dayNightCourse,
        schoolCourse,
        from,
        to,
        pIndex,
        pSize,
        stats
    )
}
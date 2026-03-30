package com.github.mrjimin.keis.api.dsl.builder

import com.github.mrjimin.keis.api.dsl.KeisDsl
import com.github.mrjimin.keis.api.dsl.query.ScheduleQuery
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
) {
    private var from: LocalDate = startOfWeek()
    private var to: LocalDate = endOfWeek()
    private var dayNightCourse: DayNightCourse? = null
    private var schoolCourse: SchoolCourse? = null

    fun date(date: LocalDate) {
        from = date
        to = date
    }

    fun dateRange(from: LocalDate, to: LocalDate) {
        this.from = from
        this.to = to
    }

    fun today() {
        val now = LocalDate.now()
        from = now
        to = now
    }

    fun thisWeek() {
        from = startOfWeek()
        to = endOfWeek()
    }

    fun dayNightCourse(course: DayNightCourse) {
        this.dayNightCourse = course
    }

    fun schoolCourse(course: SchoolCourse) {
        this.schoolCourse = course
    }

    fun build(): ScheduleQuery = ScheduleQuery(
        office,
        schoolCode,
        dayNightCourse,
        schoolCourse,
        from,
        to
    )
}
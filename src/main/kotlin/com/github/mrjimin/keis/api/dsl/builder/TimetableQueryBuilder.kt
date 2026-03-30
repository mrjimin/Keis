package com.github.mrjimin.keis.api.dsl.builder

import com.github.mrjimin.keis.api.dsl.KeisDsl
import com.github.mrjimin.keis.api.dsl.query.TimetableQuery
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.internal.endOfWeek
import com.github.mrjimin.keis.internal.startOfWeek
import java.time.LocalDate

@KeisDsl
class TimetableQueryBuilder(
    private val office: EducationOffice,
    private val schoolCode: Int,
    private val schoolType: SchoolType
): Builder<TimetableQuery> {
    private var from: LocalDate = startOfWeek()
    private var to: LocalDate = endOfWeek()
    private var grade: Int? = null
    private var classNumber: Int? = null
    private var major: String? = null
    private var fillMissing: Boolean = false
    private var maxPeriod: Int? = null

    fun grade(value: Int) {
        this.grade = value
    }

    fun classNumber(value: Int) {
        this.classNumber = value
    }

    fun major(value: String) {
        this.major = value
    }

    fun date(date: LocalDate) {
        this.from = date
        this.to = date
    }

    fun dateRange(from: LocalDate, to: LocalDate) {
        this.from = from
        this.to = to
    }

    fun today() {
        val now = LocalDate.now()
        this.from = now
        this.to = now
    }

    fun thisWeek() {
        this.from = startOfWeek()
        this.to = endOfWeek()
    }

    fun fillMissing(enable: Boolean = true) {
        this.fillMissing = enable
    }

    fun maxPeriod(value: Int) {
        this.maxPeriod = value
    }

    fun periodRange(range: IntRange) {
        this.maxPeriod = range.last
    }

    override fun build(): TimetableQuery = TimetableQuery(
        office,
        schoolCode,
        schoolType,
        from,
        to,
        grade,
        classNumber,
        major,
        fillMissing,
        maxPeriod ?: schoolType.defaultMaxPeriod
    )
}
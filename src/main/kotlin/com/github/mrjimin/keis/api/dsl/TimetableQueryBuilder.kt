package com.github.mrjimin.keis.api.dsl

import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.internal.*
import com.github.mrjimin.keis.model.query.TimetableQuery
import java.time.LocalDate

@KeisDsl
class TimetableQueryBuilder(
    private val officeCode: String,
    private val schoolCode: String,
    private val schoolType: SchoolType
) {
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

    fun build(): TimetableQuery {
        return TimetableQuery(
            officeCode = officeCode,
            schoolCode = schoolCode,
            schoolType = schoolType,
            from = from,
            to = to,
            grade = grade,
            classNumber = classNumber,
            major = major,
            fillMissing = fillMissing,
            maxPeriod = maxPeriod ?: schoolType.defaultMaxPeriod
        )
    }
}
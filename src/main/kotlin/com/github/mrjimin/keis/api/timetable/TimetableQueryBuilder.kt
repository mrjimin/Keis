package com.github.mrjimin.keis.api.timetable

import com.github.mrjimin.keis.internal.dsl.KeisDsl
import com.github.mrjimin.keis.internal.dsl.Builder
import com.github.mrjimin.keis.internal.dsl.DateRange
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
): Builder<TimetableQuery>, DateRange {
    override var from: LocalDate = startOfWeek()
    override var to: LocalDate = endOfWeek()
    override var pIndex: Int = 1
    override var pSize: Int = 100
    override var stats: Boolean = false

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
        maxPeriod ?: schoolType.defaultMaxPeriod,
        pIndex,
        pSize,
        stats
    )
}
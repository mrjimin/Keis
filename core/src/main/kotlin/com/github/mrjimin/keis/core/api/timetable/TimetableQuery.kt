package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.query.DateQuery

class TimetableQuery : DateQuery(
    "TI_YMD",
    "TI_FROM_YMD",
    "TI_TO_YMD"
) {

    internal var fill = false

    internal var maxPeriod = 7
        private set

    fun grade(grade: Int) {
        put("GRADE", grade)
    }

    fun classNumber(classNumber: Int) {
        put("CLASS_NM", classNumber)
    }

    fun major(major: String) {
        put("DDDEP_NM", major)
    }

    fun fill(enable: Boolean = true) {
        fill = enable
    }

    fun maxPeriod(value: Int) {
        require(value > 0)
        maxPeriod = value
    }

}
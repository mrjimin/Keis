package com.github.mrjimin.keis.core.query

import com.github.mrjimin.keis.core.api.school.SchoolQuery
import com.github.mrjimin.keis.core.internal.*
import java.time.LocalDate

abstract class DateQuery(
    private val singleKey: String,
    private val fromKey: String,
    private val toKey: String
) : SchoolQuery() {

    fun today() {
        put(singleKey, now().toYmd())
    }

    fun date(date: LocalDate) {
        range(date, date)
    }

    fun range(from: LocalDate, to: LocalDate) {
        put(fromKey, from.toYmd())
        put(toKey, to.toYmd())
    }

    fun thisWeek() {
        range(startOfWeek(), endOfWeek())
    }

    fun thisMonth() {
        range(startOfMonth(), endOfMonth())
    }
}
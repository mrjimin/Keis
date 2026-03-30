package com.github.mrjimin.keis.internal.dsl

import com.github.mrjimin.keis.internal.endOfWeek
import com.github.mrjimin.keis.internal.startOfWeek
import java.time.LocalDate

interface DateRange {
    var from: LocalDate
    var to: LocalDate
}

fun <T: DateRange> T.date(date: LocalDate): T = apply {
    from = date
    to = date
}

fun <T: DateRange> T.dateRange(from: LocalDate, to: LocalDate, ) = apply {
    this.from = from
    this.to = to
}

fun <T: DateRange> T.today() = apply {
    val now = LocalDate.now()
    this.from = now
    this.to = now
}

fun <T: DateRange> T.thisWeek() = apply {
    this.from = startOfWeek()
    this.to = endOfWeek()
}
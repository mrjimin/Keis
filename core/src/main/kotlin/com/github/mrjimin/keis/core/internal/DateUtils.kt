package com.github.mrjimin.keis.core.internal

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

internal fun now(): LocalDate =
    LocalDate.now(KST)

internal fun startOfWeek(): LocalDate =
    now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

internal fun endOfWeek(): LocalDate =
    now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

internal fun startOfMonth(): LocalDate =
    now().with(TemporalAdjusters.firstDayOfMonth())

internal fun endOfMonth(): LocalDate =
    now().with(TemporalAdjusters.lastDayOfMonth())

internal fun startOfYear(): LocalDate =
    now().with(TemporalAdjusters.firstDayOfYear())

internal fun endOfYear(): LocalDate =
    now().with(TemporalAdjusters.lastDayOfYear())

internal fun LocalDate.toYmd(): String =
    format(YMD_FORMATTER)

internal fun String.toLocalDate(): LocalDate =
    LocalDate.parse(this, YMD_FORMATTER)

val LocalDate.isWeekend: Boolean
    get() = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

val LocalDate.isWeekday: Boolean
    get() = !isWeekend

internal fun endOfWeekdayWeek(): LocalDate =
    now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))

internal fun LocalDate.adjustToWeekday(): LocalDate = when (dayOfWeek) {
    DayOfWeek.SATURDAY -> this.minusDays(1)
    DayOfWeek.SUNDAY -> this.plusDays(1)
    else -> this
}
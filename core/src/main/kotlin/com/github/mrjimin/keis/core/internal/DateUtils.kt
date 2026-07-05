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
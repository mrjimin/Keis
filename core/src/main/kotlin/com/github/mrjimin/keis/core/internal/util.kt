package com.github.mrjimin.keis.core.internal

import kotlinx.serialization.json.Json
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

val json: Json = Json {
    ignoreUnknownKeys = true
}

private val zone = ZoneId.of("Asia/Seoul")

private val formatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyyMMdd").withZone(zone)

internal fun now(): LocalDate =
    LocalDate.now(zone)

internal fun LocalDate.toYmd(): String =
    format(formatter)

internal fun startOfWeek(): LocalDate =
    now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

internal fun endOfWeek(): LocalDate =
    now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

internal fun startOfMonth(): LocalDate =
    now().with(TemporalAdjusters.firstDayOfMonth())

internal fun endOfMonth(): LocalDate =
    now().with(TemporalAdjusters.lastDayOfMonth())

internal fun String.toLocalDate(): LocalDate =
    LocalDate.parse(this, formatter)
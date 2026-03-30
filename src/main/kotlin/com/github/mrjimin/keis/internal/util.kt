package com.github.mrjimin.keis.internal

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
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
private val dateFormat: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyyMMdd")
        .withZone(zone)

fun LocalDate.toYmd(): String = this.format(dateFormat)

internal val defaultClient: HttpClient by lazy {
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }
}

fun startOfWeek(): LocalDate =
    LocalDate.now(zone).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

fun endOfWeek(): LocalDate =
    LocalDate.now(zone).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

fun String.toLocalDate(): LocalDate =
    LocalDate.from(dateFormat.parse(this))
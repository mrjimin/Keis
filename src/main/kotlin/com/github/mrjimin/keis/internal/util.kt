package com.github.mrjimin.keis.internal

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

val json: Json = Json {
    ignoreUnknownKeys = true
}

private val zone = ZoneId.of("Asia/Seoul")
val dateFormat: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyyMMdd")
        .withZone(zone)

val defaultClient: HttpClient by lazy {
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
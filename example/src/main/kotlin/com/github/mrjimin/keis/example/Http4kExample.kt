package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.core.api.school.schoolContext
import com.github.mrjimin.keis.example.common.mealExample
import com.github.mrjimin.keis.example.common.scheduleExample
import com.github.mrjimin.keis.example.common.schoolExample
import com.github.mrjimin.keis.example.common.timetableExample
import com.github.mrjimin.keis.http4k.keisHttp4k

suspend fun main() {
    // keisHttp4k(key: String, client: HttpHandler)
    val client = keisHttp4k("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

    schoolExample(client)

    mealExample(context)
    scheduleExample(context)
    timetableExample(context)
}
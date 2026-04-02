package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.core.api.school.schoolContext
import com.github.mrjimin.keis.example.common.mealExample
import com.github.mrjimin.keis.example.common.scheduleExample
import com.github.mrjimin.keis.example.common.schoolExample
import com.github.mrjimin.keis.example.common.timetableExample
import com.github.mrjimin.keis.ktor.keisKtor

suspend fun main() {
    // keisKtor(key: String, client: HttpClient)
    val client = keisKtor("YOUR_API_KEY")
    val context = client.schoolContext("우석고") ?: return

    schoolExample(client)

    mealExample(context)
    scheduleExample(context)
    timetableExample(context)
}
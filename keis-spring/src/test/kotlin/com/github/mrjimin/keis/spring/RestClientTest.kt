package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.api.school.schoolContext
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class RestClientTest {

    @Test
    fun restClientTest() = runBlocking {
        val key = Dotenv.configure().directory("../").load()
        val client = keisRestClient(key.get("YOUR_API_KEY"))
        val context = client.schoolContext("우석고") ?: return@runBlocking
        println(context.school)
    }
}
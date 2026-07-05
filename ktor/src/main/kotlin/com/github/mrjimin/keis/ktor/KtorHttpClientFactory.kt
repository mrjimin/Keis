package com.github.mrjimin.keis.ktor

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

fun createKtorHttpClient(
    engine: HttpClientEngineFactory<*> = OkHttp
): HttpClient = HttpClient(engine) {
    install(ContentNegotiation) {
        json()
    }
}
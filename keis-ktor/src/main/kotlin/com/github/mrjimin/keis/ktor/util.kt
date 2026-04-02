package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.internal.json
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

internal val defaultClient: HttpClient by lazy {
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }
}
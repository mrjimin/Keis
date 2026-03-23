package com.github.mrjimin.keis

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

class KeisClient(
    val config: KeisConfig,
    val client: HttpClient = defaultClient
) {
    constructor(key: String) : this(
        config = KeisConfig(key)
    )

    companion object {
        val defaultClient: HttpClient by lazy {
            HttpClient(CIO) {
                install(ContentNegotiation) {
                    register(ContentType.Any, KotlinxSerializationConverter(Json { ignoreUnknownKeys = true }))
                }
            }
        }
    }

    suspend inline fun <reified T> request(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = client.get("https://open.neis.go.kr/hub/$endpoint") {
        accept(ContentType.Any)

        parameter("KEY", config.key)
        parameter("Type", config.type.value)
        parameter("pIndex", config.pIndex)
        parameter("pSize", config.pSize)

        if (config.stats) parameter("stats", "true")

        block()
    }.body()

}

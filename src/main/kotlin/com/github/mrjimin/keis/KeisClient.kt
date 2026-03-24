package com.github.mrjimin.keis

import com.github.mrjimin.keis.enums.KeisAPI
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*

class KeisClient(
    val config: KeisConfig,
    val client: HttpClient = defaultClient,
) {
    constructor(key: String) : this(
        config = KeisConfig(key)
    )

    companion object {
        const val BASE_URL = "https://open.neis.go.kr/hub"

        val defaultClient: HttpClient by lazy {
            HttpClient(CIO) {
                install(ContentNegotiation) {
                    register(ContentType.Any, KotlinxSerializationConverter(KeisModule.json))
                }
            }
        }
    }

    suspend inline fun <reified T> request(
        api: KeisAPI,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): T = client.get("$BASE_URL/${api.value}") {
        accept(ContentType.Any)

        parameter("KEY", config.key)
        parameter("Type", config.type.value)
        parameter("pIndex", config.pIndex)
        parameter("pSize", config.pSize)

        if (config.stats) parameter("stats", "true")

        builder()
    }.body()
}
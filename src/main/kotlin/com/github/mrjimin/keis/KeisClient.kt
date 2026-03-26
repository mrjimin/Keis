package com.github.mrjimin.keis

import com.github.mrjimin.keis.dto.common.KeisWrapper
import com.github.mrjimin.keis.internal.KeisModule.json
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.*

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
                    json(json)
                }
            }
        }
    }

    internal suspend inline fun <reified T> requestRows(
        endpoint: String,
        noinline builder: HttpRequestBuilder.() -> Unit = {}
    ): List<T> {

        val response = request(endpoint, builder)

        val wrappers = json.decodeFromJsonElement<List<KeisWrapper<T>>>(
            response[endpoint] ?: return emptyList()
        )

        return wrappers.flatMap { it.row ?: emptyList() }
    }

    inline fun <reified T> JsonObject.parseRows(endpoint: String): List<T> {
        val wrappers = json.decodeFromJsonElement<List<KeisWrapper<T>>>(
            this[endpoint] ?: return emptyList()
        )

        return wrappers.flatMap { it.row ?: emptyList() }
    }

//
//    internal suspend inline fun <reified T : KeisEntity> requestRows(
//        endpoint: String,
//        noinline builder: HttpRequestBuilder.() -> Unit = {}
//    ): List<T> {
//        val response = request(endpoint, builder)
//
//        val rows = response[endpoint]
//            ?.jsonArray
//            ?.getOrNull(1)
//            ?.jsonObject
//            ?.get("row")
//            ?.jsonArray
//            ?: return emptyList()
//
//        return rows.mapNotNull { element ->
//            val obj = element as? JsonObject ?: return@mapNotNull null
//
//            json.decodeFromJsonElement<T>(obj).apply {
//                client = this@KeisClient
//                json = obj
//            }
//        }
//    }

//
    private suspend fun request(
        endpoint: String,
        builder: HttpRequestBuilder.() -> Unit
    ): JsonObject {
        return client.get("$BASE_URL/$endpoint") {
            accept(ContentType.Any)

            parameter("KEY", config.key)
            parameter("Type", config.type)
            parameter("pIndex", config.pIndex)
            parameter("pSize", config.pSize)

            if (config.stats) parameter("stats", "true")

            builder()
        }.body()
    }
//
//    private fun JsonObject.extractRows(endpoint: String): JsonArray? {
//        return this[endpoint]
//            ?.jsonArray
//            ?.getOrNull(1)
//            ?.jsonObject
//            ?.get("row")
//            ?.jsonArray
//    }
//
//    private inline fun <reified T : KeisEntity> decodeRows(
//        rows: JsonArray
//    ): List<T> {
//        return rows.mapNotNull { element ->
//            val obj = element as? JsonObject ?: return@mapNotNull null
//
//            json.decodeFromJsonElement<T>(obj).apply {
//                client = this@KeisClient
//                json = obj
//            }
//        }
//    }
}
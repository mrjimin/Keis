package com.github.mrjimin.keis

import com.github.mrjimin.keis.internal.*
import com.github.mrjimin.keis.internal.model.KeisWrapper
import com.github.mrjimin.keis.service.SchoolService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

class KeisClient(
    val config: KeisConfig,
    val httpClient: HttpClient = defaultClient,
) {

    constructor(key: String) : this(
        config = KeisConfig(key)
    )

    @Deprecated("확장함수 API 사용")
    val schoolService = SchoolService(this)

    suspend fun getJson(endpoint: String, builder: HttpRequestBuilder.() -> Unit = {}): JsonObject {
        return httpClient.get("https://open.neis.go.kr/hub/$endpoint") {
            accept(ContentType.Any)

            parameter("KEY", config.key)
            parameter("Type", config.type.value)
            parameter("pIndex", config.pIndex)
            parameter("pSize", config.pSize)

            if (config.stats) parameter("stats", "true")

            builder()
        }.body()
    }

    suspend inline fun <reified T> requestRows(
        endpoint: String,
        noinline builder: HttpRequestBuilder.() -> Unit = {}
    ): List<T> {
        val jsonObject = getJson(endpoint, builder)
        return jsonObject.parseRows(endpoint)
    }

    inline fun <reified T> JsonObject.parseRows(endpoint: String): List<T> {
        val wrappers = this[endpoint]?.let { json.decodeFromJsonElement<List<KeisWrapper<T>>>(it) }
            ?: return emptyList()

        return wrappers.flatMap { it.row ?: emptyList() }
    }
}
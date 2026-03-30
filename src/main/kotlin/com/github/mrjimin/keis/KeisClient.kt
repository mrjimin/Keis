package com.github.mrjimin.keis

import com.github.mrjimin.keis.internal.defaultClient
import com.github.mrjimin.keis.internal.json
import com.github.mrjimin.keis.internal.model.KeisWrapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

class KeisClient(
    val key: String,
    val httpClient: HttpClient = defaultClient,
) {

    suspend inline fun <reified T> requestRows(
        endpoint: String,
        noinline block: HttpRequestBuilder.() -> Unit = {}
    ): List<T> {
        val jsonObject = getJson(endpoint, block)
        return jsonObject.parseRows(endpoint)
    }

    @PublishedApi
    internal suspend fun getJson(
        endpoint: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): JsonObject {
        return httpClient.get("https://open.neis.go.kr/hub/$endpoint") {
            accept(ContentType.Any)

            parameter("KEY", key)
            parameter("Type", "json")

            block()
        }.body()
    }

    @PublishedApi
    internal inline fun <reified T> JsonObject.parseRows(endpoint: String): List<T> {
        val wrappers = this[endpoint]?.let {
            json.decodeFromJsonElement<List<KeisWrapper<T>>>(it)
        } ?: return emptyList()

        return wrappers.flatMap { it.row ?: emptyList() }
    }
}
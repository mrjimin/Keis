package com.github.mrjimin.keis.core

import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.internal.json
import com.github.mrjimin.keis.core.internal.model.KeisWrapper
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

class KeisClient(
    val key: String,
    val engine: HttpEngine
) {

    suspend inline fun <reified T> requestRows(
        endpoint: String,
        query: Map<String, String>
    ): List<T> {
        val response = request(endpoint, query)

        require(response.status == 200) {
            "요청 실패: ${response.status}"
        }

        val jsonObject = json.parseToJsonElement(response.body).jsonObject
        return jsonObject.parseRows(endpoint)
    }

    @PublishedApi
    internal suspend fun request(
        endpoint: String,
        query: Map<String, String>
    ): HttpResponse {
        val url = "https://open.neis.go.kr/hub/$endpoint"

        val finalQuery = query + mapOf(
            "KEY" to key,
            "Type" to "json"
        )

        return engine.get(url, finalQuery)
    }

    @PublishedApi
    internal inline fun <reified T> JsonObject.parseRows(endpoint: String): List<T> {
        val wrappers = this[endpoint]?.let {
            json.decodeFromJsonElement<List<KeisWrapper<T>>>(it)
        } ?: return emptyList()

        return wrappers.flatMap { it.row ?: emptyList() }
    }
}

fun keis(key: String, engine: HttpEngine): KeisClient = KeisClient(key, engine)
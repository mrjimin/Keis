package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

class RestClientEngine(
    private val client: RestClient
): HttpEngine {

    override suspend fun get(url: String, query: Map<String, String>): HttpResponse =
        withContext(Dispatchers.IO) {
            val response = client.get()
                .uri(buildUrlWithQuery(url, query))
                .retrieve()
                .toEntity<String>()

            HttpResponse(response.statusCode.value(), response.body ?: "")
        }
}

fun keisRestClient(key: String, client: RestClient = defaultRestClient): KeisClient =
    KeisClient(key, RestClientEngine(client))
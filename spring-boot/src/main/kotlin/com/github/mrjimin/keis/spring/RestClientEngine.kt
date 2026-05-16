package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity
import kotlin.coroutines.CoroutineContext

class RestClientEngine(
    private val client: RestClient,
    private val coroutineContext: CoroutineContext
): HttpEngine {

    override suspend fun get(url: String, query: Map<String, String>): HttpResponse =
        withContext(coroutineContext) {
            val response = client.get()
                .uri(buildUrlWithQuery(url, query))
                .retrieve()
                .toEntity<String>()

            HttpResponse(response.statusCode.value(), response.body ?: "")
        }
}

fun keisRestClient(key: String, client: RestClient = defaultRestClient, coroutineContext: CoroutineContext = Dispatchers.IO): KeisClient =
    KeisClient(key, RestClientEngine(client, coroutineContext))
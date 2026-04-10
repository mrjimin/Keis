package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity

class WebClientEngine(
    private val client: WebClient
): HttpEngine {

    override suspend fun get(url: String, query: Map<String, String>): HttpResponse {
        val response = client.get()
            .uri(buildUrlWithQuery(url, query))
            .retrieve()
            .toEntity<String>()
            .awaitSingle()

        return HttpResponse(response.statusCode.value(), response.body ?: "")
    }

}

fun keisWebClient(key: String, client: WebClient = defaultWebClient): KeisClient =
    KeisClient(key, WebClientEngine(client))
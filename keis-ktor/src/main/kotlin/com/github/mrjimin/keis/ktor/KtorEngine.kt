package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.model.http.HttpEngine
import com.github.mrjimin.keis.core.model.http.HttpResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class KtorEngine(
    private val client: HttpClient,
) : HttpEngine {

    override suspend fun get(url: String, query: Map<String, String>): HttpResponse {
        val response = client.get(url) {
            accept(ContentType.Any)
            url {
                query.forEach { (k, v) -> parameters.append(k, v) }
            }
        }
        return HttpResponse(response.status.value, response.bodyAsText())
    }

}

fun keisKtor(key: String, client: HttpClient = defaultClient): KeisClient =
    KeisClient(key, KtorEngine(client))
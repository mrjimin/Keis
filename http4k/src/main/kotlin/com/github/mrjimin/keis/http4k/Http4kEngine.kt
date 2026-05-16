package com.github.mrjimin.keis.http4k

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

class Http4kEngine(
    private val client: HttpHandler
) : HttpEngine {

    override suspend fun get(url: String, query: Map<String, String>): HttpResponse {
        val request = Request(Method.GET, url).withQuery(query)
        val response = client(request)
        return HttpResponse(response.status.code, response.bodyString())
    }

    private fun Request.withQuery(query: Map<String, String>): Request =
        query.entries.fold(this) { acc, (k, v) ->
            acc.query(k, v)
        }
}

fun keisHttp4k(key: String, client: HttpHandler = OkHttp()): KeisClient =
    KeisClient(key, Http4kEngine(client))
package com.github.mrjimin.keis.core.internal.transport

import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse

internal class KeisTransport(
    private val key: String,
    private val engine: HttpEngine
) {

    fun request(
        endpoint: String,
        query: Map<String, String>
    ): HttpResponse {

        val url = "https://open.neis.go.kr/hub/$endpoint"

        return engine.get(
            url,
            query + defaultQuery()
        )
    }

    private fun defaultQuery() =
        mapOf(
            "KEY" to key,
            "Type" to "json"
        )
}
package com.github.mrjimin.keis.api.dsl.marker

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter

interface Query {
    val pIndex: Int
    val pSize: Int
    val stats: Boolean

    fun HttpRequestBuilder.applyPaging() {
        parameter("pIndex", pIndex)
        parameter("pSize", pSize)
        if (stats) parameter("stats", "true")
    }

    fun apply(builder: HttpRequestBuilder)
}
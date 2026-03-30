package com.github.mrjimin.keis.internal.dsl

import io.ktor.client.request.*

internal interface Query {
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
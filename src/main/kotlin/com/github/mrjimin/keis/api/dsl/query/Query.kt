package com.github.mrjimin.keis.api.dsl.query

import io.ktor.client.request.HttpRequestBuilder

interface Query {
    fun apply(builder: HttpRequestBuilder)
}
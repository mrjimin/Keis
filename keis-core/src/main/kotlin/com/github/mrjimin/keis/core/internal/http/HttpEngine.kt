package com.github.mrjimin.keis.core.internal.http

interface HttpEngine {
    suspend fun get(url: String, query: Map<String, String>): HttpResponse
}
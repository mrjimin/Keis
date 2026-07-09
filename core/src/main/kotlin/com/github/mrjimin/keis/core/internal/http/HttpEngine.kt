package com.github.mrjimin.keis.core.internal.http

interface HttpEngine {
    fun get(url: String, query: Map<String,String>): HttpResponse
    suspend fun suspendingGet(url: String, query: Map<String,String>): HttpResponse
}
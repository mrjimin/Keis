package com.github.mrjimin.keis.core.query

abstract class Query {
    private val parameters = mutableMapOf<String, String>()

    fun put(key: String, value: Any?) {
        value?.let {
            parameters[key] = it.toString()
        }
    }

    fun build() = parameters
}
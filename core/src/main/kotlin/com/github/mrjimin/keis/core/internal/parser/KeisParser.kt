package com.github.mrjimin.keis.core.internal.parser

import com.github.mrjimin.keis.core.endpoint.Endpoint
import com.github.mrjimin.keis.core.internal.serialization.KeisResponse
import com.github.mrjimin.keis.core.internal.serialization.keisJson
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

internal class KeisParser(
    private val json: Json = keisJson,
) {

    fun <R, D> parse(
        body: String,
        endpoint: Endpoint<R, D>
    ): List<D> {

        val root = json.parseToJsonElement(body).jsonObject
        val array = root[endpoint.path]?.jsonArray ?: return emptyList()

        return array.flatMap { element ->
            val response = json.decodeFromJsonElement(
                KeisResponse.serializer(endpoint.serializer),
                element
            )
            response.row.orEmpty()
        }.map(endpoint.map)
    }
}
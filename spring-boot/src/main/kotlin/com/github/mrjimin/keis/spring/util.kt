package com.github.mrjimin.keis.spring

import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

internal val defaultRestClient: RestClient by lazy {
    RestClient.create()
}

internal val defaultWebClient: WebClient by lazy {
    WebClient.create()
}

internal fun buildUrlWithQuery(url: String, query: Map<String, String>): String {
    val uriBuilder = UriComponentsBuilder.fromUriString(url)

    query.forEach { (key, value) ->
        uriBuilder.queryParam(key, value)
    }
    return uriBuilder.build().toUriString()
}
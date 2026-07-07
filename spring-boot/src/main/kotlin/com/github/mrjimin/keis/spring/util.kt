package com.github.mrjimin.keis.spring

import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

/**
 * 기본 Spring [RestClient] 인스턴스입니다.
 *
 * 별도의 클라이언트를 지정하지 않은 경우 [keisRestClient]에서 사용됩니다.
 */
internal val defaultRestClient: RestClient by lazy {
    RestClient.create()
}

/**
 * 기본 Spring [WebClient] 인스턴스입니다.
 *
 * WebClient 기반 HTTP 엔진 구현에서 사용할 수 있습니다.
 */
internal val defaultWebClient: WebClient by lazy {
    WebClient.create()
}

/**
 * URL에 Query Parameter를 추가합니다.
 *
 * 전달받은 URL에 [query]의 모든 항목을 Query Parameter 형태로 추가하여 반환합니다.
 *
 * @param url 기본 URL
 * @param query 추가할 Query Parameter
 * @return Query Parameter가 포함된 URL 문자열
 */
internal fun buildUrlWithQuery(
    url: String,
    query: Map<String, String>
): String {
    val uriBuilder = UriComponentsBuilder.fromUriString(url)

    query.forEach { (key, value) ->
        uriBuilder.queryParam(key, value)
    }

    return uriBuilder.build().toUriString()
}
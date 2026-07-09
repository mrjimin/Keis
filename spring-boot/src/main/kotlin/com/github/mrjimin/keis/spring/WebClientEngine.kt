package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity

/**
 * Spring WebClient 기반 HTTP 엔진 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화와 Spring WebClient를 연결합니다.
 */
class WebClientEngine(
    private val client: WebClient
) : HttpEngine {

    /**
     * 비동기 GET 요청을 수행합니다.
     *
     * @param url 요청 URL
     * @param query Query Parameter
     */
    override suspend fun suspendingGet(
        url: String,
        query: Map<String, String>
    ): HttpResponse {
        val response = client.get()
            .uri(buildUrlWithQuery(url, query))
            .retrieve()
            .toEntity<String>()
            .awaitSingle()

        return HttpResponse(response.statusCode.value(), response.body ?: "")
    }

    /**
     * GET 요청을 수행합니다.
     *
     * @param url 요청 URL
     * @param query Query Parameter
     */
    override fun get(
        url: String,
        query: Map<String, String>
    ): HttpResponse = runBlocking {
        suspendingGet(url, query)
    }
}

/**
 * Spring WebClient 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [defaultWebClient]를 사용합니다.
 *
 * 사용자 정의 [WebClient]를 전달할 수 있습니다.
 *
 * ```
 * val client = keisWebClient(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key API 인증 키
 * @param client HTTP 요청에 사용할 클라이언트
 */
fun keisWebClient(
    key: String,
    client: WebClient = defaultWebClient
): KeisClient = keis(key, WebClientEngine(client))
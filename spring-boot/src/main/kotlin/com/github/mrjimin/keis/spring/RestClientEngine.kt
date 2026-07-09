package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

/**
 * Spring RestClient 기반 HTTP 엔진 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화와 Spring RestClient를 연결합니다.
 */
class RestClientEngine(
    private val client: RestClient,
) : HttpEngine {

    /**
     * GET 요청을 수행합니다.
     *
     * @param url 요청 URL
     * @param query 요청 파라미터
     */
    override fun get(
        url: String,
        query: Map<String, String>
    ): HttpResponse {
        val response = client.get()
            .uri(buildUrlWithQuery(url, query))
            .retrieve()
            .toEntity<String>()

        return HttpResponse(response.statusCode.value(), response.body ?: "")
    }

    /**
     * GET 요청을 수행합니다.
     *
     * @param url 요청 URL
     * @param query 요청 파라미터
     */
    override suspend fun suspendingGet(
        url: String,
        query: Map<String, String>
    ): HttpResponse = withContext(Dispatchers.IO) {
        get(url, query)
    }

}

/**
 * Spring RestClient 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [defaultRestClient]를 사용합니다.
 *
 * 사용자 정의 [RestClient]를 전달할 수 있습니다.
 *
 * ```
 * val client = keisRestClient(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key API 인증 키
 * @param client HTTP 요청에 사용할 클라이언트
 */
fun keisRestClient(
    key: String,
    client: RestClient = defaultRestClient,
): KeisClient = keis(key, RestClientEngine(client))
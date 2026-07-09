package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity

/**
 * Spring [WebClient]를 사용하여 HTTP 요청을 처리하는 [HttpEngine] 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화 계층과 Spring WebFlux 클라이언트를 연결합니다.
 *
 * Reactor 기반의 비동기 요청을 Kotlin Coroutines 환경에서 사용할 수 있도록
 * 변환하여 제공합니다.
 *
 * @property client HTTP 요청을 수행할 Spring WebClient
 */
class WebClientEngine(
    private val client: WebClient
) : HttpEngine {

    /**
     * GET 요청을 수행합니다.
     *
     * 전달받은 Query Parameter를 URL에 추가한 뒤
     * Spring WebClient를 통해 비동기 요청을 수행합니다.
     *
     * @param url 요청 URL
     * @param query 요청에 추가할 Query Parameter
     * @return KEIS Core에서 사용하는 HTTP 응답 객체
     */
    override fun get(
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
}

/**
 * Spring WebClient 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [defaultWebClient]를 사용하며,
 * 필요에 따라 사용자 정의 [WebClient]를 주입할 수 있습니다.
 *
 * ```
 * val client = keisWebClient(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key KEIS API 인증 키
 * @param client HTTP 요청에 사용할 WebClient
 * @return Spring WebClient를 사용하는 KEIS 클라이언트
 */
fun keisWebClient(
    key: String,
    client: WebClient = defaultWebClient
): KeisClient = keis(key, WebClientEngine(client))
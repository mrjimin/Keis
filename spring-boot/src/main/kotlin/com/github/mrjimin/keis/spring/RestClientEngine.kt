package com.github.mrjimin.keis.spring

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity
import kotlin.coroutines.CoroutineContext

/**
 * Spring [RestClient]를 사용하여 HTTP 요청을 처리하는 [HttpEngine] 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화 계층과 Spring HTTP 클라이언트를 연결합니다.
 *
 * 기본적으로 IO 작업을 위한 [Dispatchers.IO]에서 요청을 수행하며,
 * 필요에 따라 다른 [CoroutineContext]를 지정할 수 있습니다.
 *
 * @property client HTTP 요청을 수행할 Spring RestClient
 * @property coroutineContext HTTP 요청을 실행할 CoroutineContext
 */
class RestClientEngine(
    private val client: RestClient,
    private val coroutineContext: CoroutineContext
) : HttpEngine {

    /**
     * GET 요청을 수행합니다.
     *
     * 전달받은 Query Parameter를 URL에 추가한 뒤
     * Spring RestClient를 통해 요청합니다.
     *
     * 네트워크 요청은 지정된 [coroutineContext]에서 실행됩니다.
     *
     * @param url 요청 URL
     * @param query 요청에 추가할 Query Parameter
     * @return KEIS Core에서 사용하는 HTTP 응답 객체
     */
    override suspend fun get(
        url: String,
        query: Map<String, String>
    ): HttpResponse =
        withContext(coroutineContext) {
            val response = client.get()
                .uri(buildUrlWithQuery(url, query))
                .retrieve()
                .toEntity<String>()

            HttpResponse(response.statusCode.value(), response.body ?: "")
        }
}

/**
 * Spring RestClient 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [defaultRestClient]를 사용하며,
 * 필요에 따라 사용자 정의 [RestClient]와 [CoroutineContext]를 주입할 수 있습니다.
 *
 * ```
 * val client = keisRestClient(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key KEIS API 인증 키
 * @param client HTTP 요청에 사용할 Spring RestClient
 * @param coroutineContext HTTP 요청을 실행할 CoroutineContext
 * @return Spring RestClient를 사용하는 KEIS 클라이언트
 */
fun keisRestClient(
    key: String,
    client: RestClient = defaultRestClient,
    coroutineContext: CoroutineContext = Dispatchers.IO
): KeisClient = keis(key, RestClientEngine(client, coroutineContext))
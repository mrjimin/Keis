package com.github.mrjimin.keis.http4k

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

/**
 * http4k를 사용하여 HTTP 요청을 처리하는 [HttpEngine] 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화 계층과 http4k 클라이언트를 연결합니다.
 *
 * @property client HTTP 요청을 실행할 http4k 핸들러
 */
class Http4kEngine(
    private val client: HttpHandler
) : HttpEngine {

    /**
     * GET 요청을 수행합니다.
     *
     * 전달받은 쿼리 파라미터를 URL Query Parameter로 추가한 뒤
     * http4k 클라이언트를 통해 요청합니다.
     *
     * @param url 요청 URL
     * @param query 요청에 추가할 Query Parameter
     * @return KEIS Core에서 사용하는 HTTP 응답 객체
     */
    override suspend fun get(
        url: String,
        query: Map<String, String>
    ): HttpResponse {
        val request = Request(Method.GET, url).withQuery(query)
        val response = client(request)
        return HttpResponse(response.status.code, response.bodyString())
    }

    /**
     * Request에 Query Parameter를 추가합니다.
     *
     * @param query 추가할 Query Parameter
     * @return Query Parameter가 추가된 요청
     */
    private fun Request.withQuery(query: Map<String, String>): Request =
        query.entries.fold(this) { acc, (k, v) ->
            acc.query(k, v)
        }
}

/**
 * http4k 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [OkHttp]를 사용하며,
 * 필요에 따라 다른 http4k [HttpHandler] 구현체를 주입할 수 있습니다.
 *
 * ```
 * val client = keisHttp4k(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key KEIS API 인증 키
 * @param client HTTP 요청을 처리할 http4k 핸들러
 * @return http4k를 사용하는 KEIS 클라이언트
 */
fun keisHttp4k(
    key: String,
    client: HttpHandler = OkHttp()
): KeisClient = keis(key, Http4kEngine(client))
package com.github.mrjimin.keis.http4k

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

/**
 * http4k 기반 HTTP 엔진 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화와 http4k 클라이언트를 연결합니다.
 */
class Http4kEngine(
    private val client: HttpHandler
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
        val request = Request(Method.GET, url).withQuery(query)
        val response = client(request)
        return HttpResponse(response.status.code, response.bodyString())
    }

    override suspend fun suspendingGet(
        url: String,
        query: Map<String, String>
    ): HttpResponse = withContext(Dispatchers.IO) {
        get(url, query)
    }

    /**
     * 요청에 Query Parameter를 추가합니다.
     *
     * @param query 추가할 요청 파라미터
     */
    private fun Request.withQuery(query: Map<String, String>): Request =
        query.entries.fold(this) { acc, (k, v) ->
            acc.query(k, v)
        }
}

/**
 * http4k 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [OkHttp]를 사용합니다.
 *
 * 다른 http4k [HttpHandler] 구현체를 사용하려면 직접 전달할 수 있습니다.
 *
 * ```
 * val client = keisHttp4k(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key API 인증 키
 * @param client HTTP 요청을 처리할 핸들러
 */
fun keisHttp4k(
    key: String,
    client: HttpHandler = OkHttp()
): KeisClient = keis(key, Http4kEngine(client))
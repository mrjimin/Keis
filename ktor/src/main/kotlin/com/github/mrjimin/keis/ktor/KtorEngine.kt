package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

/**
 * Ktor [HttpClient]를 사용하여 HTTP 요청을 처리하는 [HttpEngine] 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화 계층과 Ktor HTTP 클라이언트를 연결합니다.
 *
 * Ktor는 Kotlin Coroutines 기반의 비동기 HTTP 클라이언트이므로
 * 별도의 스레드 전환 없이 요청을 수행합니다.
 *
 * @property client HTTP 요청을 수행할 Ktor HttpClient
 */
class KtorEngine(
    private val client: HttpClient,
) : HttpEngine {

    /**
     * GET 요청을 수행합니다.
     *
     * 전달받은 Query Parameter를 요청 URL에 추가한 뒤
     * Ktor 클라이언트를 통해 요청합니다.
     *
     * @param url 요청 URL
     * @param query 요청에 추가할 Query Parameter
     * @return KEIS Core에서 사용하는 HTTP 응답 객체
     */
    override fun get(
        url: String,
        query: Map<String, String>
    ): HttpResponse {
        val response = client.get(url) {
            accept(ContentType.Any)
            url {
                query.forEach { (k, v) ->
                    parameters.append(k, v)
                }
            }
        }

        return HttpResponse(
            response.status.value,
            response.bodyAsText()
        )
    }
}

/**
 * Ktor 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [createKtorHttpClient]를 사용하며,
 * 필요에 따라 사용자 정의 [HttpClient]를 주입할 수 있습니다.
 *
 * ```
 * val client = keisKtor(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key KEIS API 인증 키
 * @param client HTTP 요청에 사용할 Ktor HttpClient
 * @return Ktor를 사용하는 KEIS 클라이언트
 */
fun keisKtor(
    key: String,
    client: HttpClient = createKtorHttpClient()
): KeisClient = keis(key, KtorEngine(client))
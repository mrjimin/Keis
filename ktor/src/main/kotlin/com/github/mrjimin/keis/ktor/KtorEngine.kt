package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.http.HttpResponse
import com.github.mrjimin.keis.core.keis
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

/**
 * Ktor 기반 HTTP 엔진 구현체입니다.
 *
 * KEIS Core의 HTTP 추상화와 Ktor 클라이언트를 연결합니다.
 */
class KtorEngine(
    private val client: HttpClient,
) : HttpEngine {

    /**
     * GET 요청을 수행합니다.
     *
     * @param url 요청 URL
     * @param query 요청 파라미터
     */
    override suspend fun suspendingGet(
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

    override fun get(
        url: String,
        query: Map<String, String>
    ): HttpResponse = runBlocking {
        suspendingGet(url, query)
    }
}

/**
 * Ktor 기반 KEIS API 클라이언트를 생성합니다.
 *
 * 기본 HTTP 클라이언트로 [createKtorHttpClient]를 사용합니다.
 *
 * 사용자 정의 [HttpClient]를 전달할 수 있습니다.
 *
 * ```
 * val client = keisKtor(
 *     key = "API_KEY"
 * )
 * ```
 *
 * @param key API 인증 키
 * @param client HTTP 요청에 사용할 클라이언트
 */
fun keisKtor(
    key: String,
    client: HttpClient = createKtorHttpClient()
): KeisClient = keis(key, KtorEngine(client))
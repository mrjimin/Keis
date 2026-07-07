package com.github.mrjimin.keis.ktor

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

/**
 * 기본 설정이 적용된 Ktor [HttpClient]를 생성합니다.
 *
 * [ContentNegotiation] 플러그인이 설치되어 있으며,
 * JSON 직렬화를 위해 Kotlinx Serialization을 사용합니다.
 *
 * 기본 엔진으로 [OkHttp]를 사용하지만,
 * 필요에 따라 다른 [HttpClientEngineFactory]를 지정할 수 있습니다.
 *
 * ```
 * val client = createKtorHttpClient()
 * ```
 *
 * @param engine 사용할 Ktor HTTP 엔진
 * @return 설정이 완료된 Ktor HttpClient
 */
fun createKtorHttpClient(
    engine: HttpClientEngineFactory<*> = OkHttp
): HttpClient = HttpClient(engine) {
    install(ContentNegotiation) {
        json()
    }
}
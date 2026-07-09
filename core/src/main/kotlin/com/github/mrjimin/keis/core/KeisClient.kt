package com.github.mrjimin.keis.core

import com.github.mrjimin.keis.core.endpoint.Endpoint
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.parser.KeisParser
import com.github.mrjimin.keis.core.internal.transport.KeisTransport
import com.github.mrjimin.keis.core.query.Query

/**
 * KEIS Open API를 호출하는 클라이언트입니다.
 *
 * [keis]를 사용해 생성할 수 있습니다.
 */
class KeisClient internal constructor(
    private val transport: KeisTransport,
    private val parser: KeisParser
) {

    /**
     * API를 호출하고 응답을 파싱합니다.
     *
     * 현재 스레드를 차단하는 요청입니다.
     *
     * @throws IllegalArgumentException 응답 상태 코드가 200이 아닌 경우
     */
    internal fun <P : Query, R, D> KeisClient.fetch(
        endpoint: Endpoint<R, D>,
        queryFactory: () -> P,
        block: P.() -> Unit = {}
    ): List<D> {

        val query = queryFactory()
            .apply(block)
            .build()

        val response = transport.request(
            endpoint.path,
            query
        )

        require(response.status == 200)

        return parser.parse(response.body, endpoint)
    }

    /**
     * API를 호출하고 응답을 파싱합니다.
     *
     * 코루틴에서 사용할 수 있는 suspend 요청입니다.
     *
     * @throws IllegalArgumentException 응답 상태 코드가 200이 아닌 경우
     */
    internal suspend fun <P : Query, R, D> KeisClient.suspendingFetch(
        endpoint: Endpoint<R, D>,
        queryFactory: () -> P,
        block: P.() -> Unit = {}
    ): List<D> {

        val query = queryFactory()
            .apply(block)
            .build()

        val response = transport.suspendingRequest(
            endpoint.path,
            query
        )

        require(response.status == 200)

        return parser.parse(response.body, endpoint)
    }

}

/**
 * KEIS API 클라이언트를 생성합니다.
 *
 * @param key API 인증 키
 * @param engine HTTP 엔진
 */
fun keis(
    key: String,
    engine: HttpEngine
): KeisClient = KeisClient(
    KeisTransport(key, engine),
    KeisParser()
)
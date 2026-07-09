package com.github.mrjimin.keis.core

import com.github.mrjimin.keis.core.endpoint.Endpoint
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.parser.KeisParser
import com.github.mrjimin.keis.core.internal.transport.KeisTransport
import com.github.mrjimin.keis.core.query.Query

/**
 * KEIS API 요청을 수행하는 클라이언트입니다.
 *
 * [keis] 함수를 통해 생성할 수 있으며,
 * 급식, 학사일정, 시간표, 학교 정보 등의 조회 API를 제공합니다.
 *
 * @property transport API 요청을 담당하는 전송 계층
 * @property parser API 응답을 도메인 객체로 변환하는 파서
 */
class KeisClient internal constructor(
    private val transport: KeisTransport,
    private val parser: KeisParser
) {

    /**
     * KEIS API 요청을 수행하고 응답 데이터를 파싱합니다.
     *
     * 각 API 조회 함수(`meals`, `schedules`, `timetables` 등)에서
     * 공통으로 사용하는 내부 요청 메서드입니다.
     *
     * @param endpoint 요청할 API 엔드포인트
     * @param queryFactory 요청 조건 객체 생성 함수
     * @param block 요청 조건을 설정하는 DSL
     * @return 파싱된 API 응답 데이터 목록
     * @throws IllegalArgumentException API 응답 상태 코드가 성공(200)이 아닌 경우
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

}

/**
 * KEIS API 클라이언트를 생성합니다.
 *
 * 생성된 클라이언트를 통해 학교, 급식, 학사일정, 시간표 등의 정보를 조회할 수 있습니다.
 *
 * ```
 * val client = keis(
 *     key = "API_KEY",
 *     engine = engine
 * )
 * ```
 *
 * @param key KEIS API 인증 키
 * @param engine HTTP 요청을 처리할 엔진
 * @return 생성된 KEIS 클라이언트
 */
fun keis(
    key: String,
    engine: HttpEngine
): KeisClient = KeisClient(
    KeisTransport(key, engine),
    KeisParser()
)
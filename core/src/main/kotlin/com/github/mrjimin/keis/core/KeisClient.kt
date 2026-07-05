package com.github.mrjimin.keis.core

import com.github.mrjimin.keis.core.endpoint.Endpoint
import com.github.mrjimin.keis.core.internal.http.HttpEngine
import com.github.mrjimin.keis.core.internal.parser.KeisParser
import com.github.mrjimin.keis.core.internal.transport.KeisTransport
import com.github.mrjimin.keis.core.query.Query

class KeisClient internal constructor(
    private val transport: KeisTransport,
    private val parser: KeisParser
) {

    internal suspend fun <P : Query, R, D> KeisClient.fetch(
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

fun keis(
    key: String,
    engine: HttpEngine
): KeisClient = KeisClient(
    KeisTransport(key, engine),
    KeisParser()
)
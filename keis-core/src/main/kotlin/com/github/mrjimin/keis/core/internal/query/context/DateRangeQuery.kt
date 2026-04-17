package com.github.mrjimin.keis.core.internal.query.context

import com.github.mrjimin.keis.core.internal.*
import java.time.LocalDate

interface DateRangeQuery : QueryContext {
    val fromKey: String
    val toKey: String

    fun today() = apply {
        dateRange { today() }
    }

    fun dateRange(block: Builder.() -> Unit) = apply {
        Builder(this, fromKey, toKey).apply(block)
    }

    class Builder(
        private val ctx: QueryContext,
        private val fromKey: String,
        private val toKey: String
    ) {

        fun from(date: LocalDate) = apply {
            ctx.put(fromKey, date.toYmd())
        }

        fun to(date: LocalDate) = apply {
            ctx.put(toKey, date.toYmd())
        }

        fun single(date: LocalDate) = apply {
            val ymd = date.toYmd()
            ctx.put(fromKey, ymd)
            ctx.put(toKey, ymd)
        }

        fun today() = single(now())

        fun thisWeek() = apply {
            from(startOfWeek())
            to(endOfWeek())
        }

        fun thisMonth() = apply {
            from(startOfMonth())
            to(endOfMonth())
        }

        fun range(from: LocalDate, to: LocalDate) = apply {
            require(!from.isAfter(to)) {
                "시작 날짜는 종료 날짜보다 같거나 이전이어야 합니다."
            }
            from(from)
            to(to)
        }
    }
}
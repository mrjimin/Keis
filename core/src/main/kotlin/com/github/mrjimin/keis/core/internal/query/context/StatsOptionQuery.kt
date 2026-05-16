package com.github.mrjimin.keis.core.internal.query.context

interface StatsOptionQuery : QueryContext {
    fun stats(enabled: Boolean = true) = apply {
        put("stats", enabled)
    }
}
package com.github.mrjimin.keis.core.model.query.context

interface StatsOptionQuery : QueryContext {
    fun stats(enabled: Boolean = true) = apply {
        put("stats", enabled)
    }
}
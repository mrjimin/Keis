package com.github.mrjimin.keis.core.model.query.context

import com.github.mrjimin.keis.core.model.query.QueryContext

interface StatsOptionQuery : QueryContext {
    fun stats(enabled: Boolean = true) = apply {
        put("stats", enabled)
    }
}
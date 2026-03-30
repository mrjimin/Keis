package com.github.mrjimin.keis.api.dsl.builder

import com.github.mrjimin.keis.api.dsl.query.Query

interface Builder<T : Query> {
    fun build(): T
}
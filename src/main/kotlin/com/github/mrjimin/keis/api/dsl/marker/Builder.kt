package com.github.mrjimin.keis.api.dsl.marker

interface Builder<T : Query> : Pageable {
    fun build(): T
}
package com.github.mrjimin.keis.internal.dsl

internal interface Builder<T : Query> : Pageable {
    fun build(): T
}
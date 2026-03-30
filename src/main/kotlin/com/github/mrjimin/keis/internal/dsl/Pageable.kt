package com.github.mrjimin.keis.internal.dsl

interface Pageable {
    var pIndex: Int
    var pSize: Int
    var stats: Boolean
}

fun <T : Pageable> T.page(index: Int = 1, size: Int = 100) = apply {
    pIndex = index
    pSize = size
}

fun <T: Pageable> T.stats(value: Boolean = true) = apply {
    stats = value
}

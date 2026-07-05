package com.github.mrjimin.keis.core.query

fun Query.page(index: Int) {
    require(index > 1)
    put("pIndex", index)
}

fun Query.size(index: Int) {
    require(index > 1)
    put("pSize", index)
}
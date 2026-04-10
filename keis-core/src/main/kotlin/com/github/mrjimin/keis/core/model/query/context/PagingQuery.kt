package com.github.mrjimin.keis.core.model.query.context

interface PagingQuery : QueryContext {
    fun page(index: Int) = apply {
        require(index >= 1) { "페이지 번호는 1 이상이어야 합니다." }
        put("pIndex", index)
    }

    fun size(index: Int) = apply {
        require(index >= 1) { "페이지 크기는 1 이상이어야 합니다." }
        put("pSize", index)
    }
}


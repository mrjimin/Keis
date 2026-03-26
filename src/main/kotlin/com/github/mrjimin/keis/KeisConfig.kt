package com.github.mrjimin.keis

import com.github.mrjimin.keis.enums.ResponseType

data class KeisConfig(
    val key: String,
    val type: ResponseType = ResponseType.JSON,
    val pIndex: Int = 1,
    val pSize: Int = 100,
    val stats: Boolean = false
)
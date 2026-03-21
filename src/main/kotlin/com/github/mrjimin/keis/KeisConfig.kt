package com.github.mrjimin.keis

import com.github.mrjimin.keis.enums.KeisType

data class KeisConfig(
    val key: String,
    val type: KeisType = KeisType.JSON,
    val pIndex: Int = 1,
    val pSize: Int = 100,
    val stats: Boolean = false
)
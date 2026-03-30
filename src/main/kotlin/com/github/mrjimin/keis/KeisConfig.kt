package com.github.mrjimin.keis

@Deprecated("page 개별 설정 가능")
data class KeisConfig(
    val key: String,
    val pIndex: Int = 1,
    val pSize: Int = 100,
    val stats: Boolean = false
)
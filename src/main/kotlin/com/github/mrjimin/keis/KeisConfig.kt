package com.github.mrjimin.keis

data class KeisConfig(
    val key: String,
    val pIndex: Int = 1,
    val pSize: Int = 100,
    val stats: Boolean = false
)
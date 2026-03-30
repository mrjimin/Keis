package com.github.mrjimin.keis.internal.model

import kotlinx.serialization.Serializable

@Serializable
internal data class KeisWrapper<T>(
    val head: List<KeisHead>? = null,
    val row: List<T>? = null
)
package com.github.mrjimin.keis.core.internal.serialization

import kotlinx.serialization.Serializable

@Serializable
internal data class KeisResponse<T>(
    val head: List<KeisHead>? = null,
    val row: List<T>? = null
)
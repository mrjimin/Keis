package com.github.mrjimin.keis.core.internal.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class KeisHead(
    @SerialName("list_total_count")
    val totalCount: Int? = null,
    val result: KeisResult? = null
)
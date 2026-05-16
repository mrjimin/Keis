package com.github.mrjimin.keis.core.internal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeisHead(
    @SerialName("list_total_count")
    val listTotalCount: Int? = null,
    val result: KeisHeadResult? = null
)
package com.github.mrjimin.keis.internal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class KeisHead(
    @SerialName("list_total_count")
    val listTotalCount: Int? = null,
    val result: KeisHeadResult? = null
)
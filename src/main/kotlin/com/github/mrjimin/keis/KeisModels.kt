package com.github.mrjimin.keis

import kotlinx.serialization.*

@Serializable
data class KeisWrapper<T>(
    val head: List<KeisHead>? = null,
    val row: List<T>? = null
)

@Serializable
data class KeisHead(
    @SerialName("list_total_count")
    val listTotalCount: Int? = null,
    val result: KeisHeadResult? = null
)

@Serializable
data class KeisHeadResult(
    val code: String,
    val message: String
)

interface KeisRowContainer<T> {
    val items: List<KeisWrapper<T>>

    val rows: List<T>
        get() = items.firstOrNull { it.row != null }?.row ?: emptyList()

    val allRows: List<T>
        get() = items.flatMap { it.row ?: emptyList() }

    val allHeads: List<KeisHead>
        get() = items.flatMap { it.head ?: emptyList() }
}
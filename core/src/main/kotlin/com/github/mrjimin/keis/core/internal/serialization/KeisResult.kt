package com.github.mrjimin.keis.core.internal.serialization

import kotlinx.serialization.Serializable

@Serializable
internal data class KeisResult(
    val code: String,
    val message: String
)
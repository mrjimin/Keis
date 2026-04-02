package com.github.mrjimin.keis.core.internal.model

import kotlinx.serialization.Serializable

@Serializable
data class KeisHeadResult(
    val code: String,
    val message: String
)
package com.github.mrjimin.keis.internal.model

import kotlinx.serialization.Serializable

@Serializable
data class KeisHeadResult(
    val code: String,
    val message: String
)
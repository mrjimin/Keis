package com.github.mrjimin.keis.core.endpoint

import kotlinx.serialization.KSerializer

abstract class Endpoint<R, D>(
    val path: String,
    val serializer: KSerializer<R>,
    val map: (R) -> D
)
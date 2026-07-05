package com.github.mrjimin.keis.core.internal.serialization

import kotlinx.serialization.json.Json

internal val keisJson: Json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false
}
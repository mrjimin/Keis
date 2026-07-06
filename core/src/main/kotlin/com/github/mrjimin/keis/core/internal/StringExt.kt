package com.github.mrjimin.keis.core.internal

internal fun String.toCalories(): Double =
    replace("Kcal", "", ignoreCase = true)
        .trim()
        .toDoubleOrNull()
        ?: 0.0
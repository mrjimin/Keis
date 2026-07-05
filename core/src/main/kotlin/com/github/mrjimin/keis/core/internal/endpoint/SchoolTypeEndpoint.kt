package com.github.mrjimin.keis.core.internal.endpoint

import com.github.mrjimin.keis.core.domain.enums.SchoolType

internal fun SchoolType.endpoint() =
    when(this) {
        SchoolType.ELEMENTARY -> "elsTimetable"
        SchoolType.MIDDLE -> "misTimetable"
        SchoolType.HIGH -> "hisTimetable"
        SchoolType.SPECIAL -> "spsTimetable"
        else -> error("unknown")
    }
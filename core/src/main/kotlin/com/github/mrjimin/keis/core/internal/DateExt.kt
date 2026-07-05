package com.github.mrjimin.keis.core.internal

import java.time.LocalDate

internal fun LocalDate.toYmd(): String =
    format(YMD_FORMATTER)

internal fun String.toLocalDate(): LocalDate =
    LocalDate.parse(this, YMD_FORMATTER)
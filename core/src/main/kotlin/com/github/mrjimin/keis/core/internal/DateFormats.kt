package com.github.mrjimin.keis.core.internal

import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal val KST = ZoneId.of("Asia/Seoul")

internal val YMD_FORMATTER: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyyMMdd").withZone(KST)
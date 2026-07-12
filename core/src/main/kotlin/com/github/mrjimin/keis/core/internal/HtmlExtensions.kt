package com.github.mrjimin.keis.core.internal

internal fun String.toPlainText(): String =
    replace(Regex("<br\\s*/?>"), "\n").trim()
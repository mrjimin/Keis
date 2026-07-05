package com.github.mrjimin.keis.core.internal

internal fun String.cleanHtml(): String =
    replace("<br/>", "\n")
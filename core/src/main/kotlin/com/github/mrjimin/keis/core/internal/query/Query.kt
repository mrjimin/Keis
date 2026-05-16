package com.github.mrjimin.keis.core.internal.query

interface Query {
    fun toMap(): Map<String, String>
}
package com.github.mrjimin.keis.core.model.query

interface Query {
    fun toMap(): Map<String, String>
}
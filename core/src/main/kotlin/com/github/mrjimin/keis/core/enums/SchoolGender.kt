package com.github.mrjimin.keis.core.enums

enum class SchoolGender(val value: String) {
    UNKNOWN("unknown"),
    MALE("남"),
    FEMALE("여"),
    COED("남여공학");

    companion object {
        private val map = entries.associateBy { it.value }
        fun from(name: String): SchoolGender = map[name] ?: UNKNOWN
    }
}
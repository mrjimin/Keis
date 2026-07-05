package com.github.mrjimin.keis.core.domain.enums

enum class SchoolGender(val label: String) {
    MALE("남"),
    FEMALE("여"),
    COED("남녀공학"),
    UNKNOWN("unknown");

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(name: String) = map[name] ?: UNKNOWN
    }
}
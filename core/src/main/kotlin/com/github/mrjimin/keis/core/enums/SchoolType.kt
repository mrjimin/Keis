package com.github.mrjimin.keis.core.enums

enum class SchoolType(val text: String, val endpoint: String, val defaultMaxPeriod: Int) {
    UNKNOWN("알수없음", "unknown", 0),
    ELEMENTARY("초등학교", "elsTimetable", 6),
    MIDDLE("중학교", "misTimetable", 7),
    HIGH("고등학교", "hisTimetable", 7),
    SPECIAL("특수학교", "spsTimetable", 7);

    companion object {
        private val map = entries.associateBy { it.text }
        fun from(name: String): SchoolType = map[name] ?: UNKNOWN
    }
}
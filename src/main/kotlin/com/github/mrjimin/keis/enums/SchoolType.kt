package com.github.mrjimin.keis.enums

enum class SchoolType(val text: String, val endpoint: String) {
    UNKNOWN("알수없음", "unknown"),
    ELEMENTARY("초등학교", "elsTimetable"),
    MIDDLE("중학교", "misTimetable"),
    HIGH("고등학교", "hisTimetable"),
    SPECIAL("특수학교", "spsTimetable");

    companion object {
        private val map = entries.associateBy { it.text }
        fun from(name: String): SchoolType = map[name] ?: UNKNOWN
    }
}
package com.github.mrjimin.keis.core.domain.enums

enum class SchoolType(val label: String, val defaultMaxPeriod: Int) {
    ELEMENTARY("초등학교", 6),
    MIDDLE("중학교", 7),
    HIGH("고등학교", 7),
    SPECIAL("특수학교", 7),
    UNKNOWN("알수없음", -1);

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(label: String?) = map[label] ?: UNKNOWN
    }
}
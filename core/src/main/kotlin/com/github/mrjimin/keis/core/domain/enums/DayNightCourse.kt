package com.github.mrjimin.keis.core.domain.enums

enum class DayNightCourse(val label: String) {
    DAY("주간"),
    NIGHT("야간"),
    UNKNOWN("알수없음");

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(label: String?) = map[label] ?: UNKNOWN
    }
}
package com.github.mrjimin.keis.enums

enum class DayNightCourse(val value: String) {
    UNKNOWN("알수없음"),
    DAY("주간"),
    NIGHT("야간");

    companion object {
        private val map = entries.associateBy { it.value }
        fun from(name: String?): DayNightCourse = map[name] ?: UNKNOWN
    }
}
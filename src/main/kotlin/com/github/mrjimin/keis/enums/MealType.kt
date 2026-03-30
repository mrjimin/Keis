package com.github.mrjimin.keis.enums

enum class MealType(val text: String, val code: Int) {
    ALL("전체", 0),
    BREAKFAST("조식", 1),
    LUNCH("중식", 2),
    DINNER("석식", 3),
    UNKNOWN("Unknown", 0);

    companion object {
        private val map = entries.associateBy { it.text }
        fun from(name: String): MealType = map[name] ?: UNKNOWN
    }
}
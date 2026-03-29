package com.github.mrjimin.keis.enums

enum class MealType(val text: String) {
    ALL("전체"),
    BREAKFAST("조식"),
    LUNCH("중식"),
    DINNER("석식"),
    UNKNOWN("Unknown");

    companion object {
        private val map = entries.associateBy { it.text }
        fun from(name: String): MealType = map[name] ?: UNKNOWN
    }
}
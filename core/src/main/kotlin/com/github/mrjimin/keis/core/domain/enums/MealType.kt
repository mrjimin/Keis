package com.github.mrjimin.keis.core.domain.enums

enum class MealType(val code: Int, val label: String) {
    BREAKFAST(1, "조식"),
    LUNCH(2, "중식"),
    DINNER(3, "석식"),
    ALL(-1,"전체"),
    UNKNOWN(0, "알수없음");

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(label: String?) = map[label] ?: UNKNOWN
    }
}
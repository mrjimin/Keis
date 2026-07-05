package com.github.mrjimin.keis.core.domain.enums

enum class FoundationType(val label: String) {
    NATIONAL("국립"),
    PUBLIC("공립"),
    PRIVATE("사립"),
    UNKNOWN("알수없음");

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(label: String?) = map[label] ?: UNKNOWN
    }

}
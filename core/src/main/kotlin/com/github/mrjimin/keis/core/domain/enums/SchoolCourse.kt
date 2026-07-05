package com.github.mrjimin.keis.core.domain.enums

enum class SchoolCourse(val label: String) {
    GENERAL("일반"),
    SPECIAL("특수"),
    BROADCAST("방송통신"),
    UNKNOWN("알수없음");

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(label: String?) = map[label] ?: UNKNOWN
    }
}
package com.github.mrjimin.keis.core.domain.enums

enum class BusinessType(val label: String) {
    GENERAL("일반계"),
    SPECIALIZED("특성화계"),
    ARTS("예술계"),
    UNKNOWN("알수없음");

    companion object {
        private val map = entries.associateBy { it.label }
        fun from(label: String?) = map[label] ?: UNKNOWN
    }

}
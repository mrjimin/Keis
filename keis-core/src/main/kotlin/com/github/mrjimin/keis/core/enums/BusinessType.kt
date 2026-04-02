package com.github.mrjimin.keis.core.enums

enum class BusinessType(val value: String) {
    UNKNOWN("알수없음"),
    GENERAL("일반계"),
    SPECIALIZED("특성화계"),
    ARTS("예술계");

    companion object {
        private val map = entries.associateBy { it.value }
        fun from(name: String?): BusinessType = map[name] ?: UNKNOWN
    }

}
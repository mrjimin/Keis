package com.github.mrjimin.keis.enums

enum class SchoolType(val korean: String) {

    UNKNOWN("알수없음"),
    INTERNATIONAL("외국인학교"),
    KINDERGARTEN("유치원"),
    ELEMENTARY("초등학교"),
    MIDDLE("중학교"),
    HIGH("고등학교"),
    SPECIAL("특수학교");

    companion object {
        private val map = entries.associateBy { it.korean }
        fun from(name: String): SchoolType = map[name] ?: UNKNOWN
    }
}
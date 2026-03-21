package com.github.mrjimin.keis.enums

enum class SchoolType(val value: String) {
    UNKNOWN("알수없음"),
    INTERNATIONAL("외국인학교"),
    KINDERGARTEN("유치원"),
    ELEMENTARY("초등학교"),
    MIDDLE("중학교"),
    HIGH("고등학교"),
    SPECIAL("특수학교");

    companion object {
        fun from(name: String): SchoolType = entries.find { it.value == name } ?: UNKNOWN
    }
}
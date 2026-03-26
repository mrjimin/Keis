package com.github.mrjimin.keis.enums

enum class EducationOffice(val text: String, val code: String) {

    UNKNOWN("알수없음", ""),
    SEOUL("서울특별시교육청", "B10"),
    BUSAN("부산광역시교육청", "C10"),
    DAEGU("대구광역시교육청", "D10"),
    INCHEON("인천광역시교육청", "E10"),
    GWANGJU("광주광역시교육청", "F10"),
    DAEJEON("대전광역시교육청", "G10"),
    ULSAN("울산광역시교육청", "H10"),
    SEJONG("세종특별자치시교육청", "I10"),
    GYEONGGI("경기도교육청", "J10"),
    GANGWON("강원특별자치도교육청", "K10"),
    CHUNGBUK("충청북도교육청", "M10"),
    CHUNGNAM("충청남도교육청", "N10"),
    JEONBUK("전북특별자치도교육청", "P10"),
    JEONNAM("전라남도교육청", "Q10"),
    GYEONGBUK("경상북도교육청", "R10"),
    GYEONGNAM("경상남도교육청", "S10"),
    JEJU("제주특별자치도교육청", "T10");

    companion object {
        private val codeMap = entries.associateBy { it.code }
        private val textMap = entries.associateBy { it.text }
        fun from(code: String, name: String = ""): EducationOffice =
            codeMap[code] ?: textMap[name] ?: UNKNOWN
    }
}
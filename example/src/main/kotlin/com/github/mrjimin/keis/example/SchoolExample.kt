package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.school
import com.github.mrjimin.keis.api.schoolContext
import com.github.mrjimin.keis.api.schools
import com.github.mrjimin.keis.enums.EducationOffice

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")

    // 이름으로 학교 조회
    val school = client.school("우석고")

    // 여러 학교 조회
    val schools = client.schools("우석")

    // 교육청 + 코드로 조회
    val schoolByCode = client.school(
        office = EducationOffice.JEONBUK,
        schoolCode = 123456
    )

    // DSL 사용
    val filtered = client.schools {
        office(EducationOffice.JEONBUK)
        name("우석")
    }

    // Context 생성
    val context = client.schoolContext("우석고")
}
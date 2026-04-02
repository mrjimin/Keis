package com.github.mrjimin.keis.example.common

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.api.school.asContext
import com.github.mrjimin.keis.core.api.school.school
import com.github.mrjimin.keis.core.api.school.schoolContext
import com.github.mrjimin.keis.core.api.school.schools
import com.github.mrjimin.keis.core.enums.EducationOffice

suspend fun schoolExample(client: KeisClient) {

    // 이름으로 학교 조회
    val school = client.school("우석고")

    // 여러 학교 조회 (이름 필터)
    val schools = client.schools {
        name("우석")
    }

    // 교육청 + 코드로 학교 조회
    val schoolByCode = client.school(EducationOffice.JEONBUK, 8321094)

    // DSL 사용
    val filteredSchools = client.schools {
        school {
            office(EducationOffice.JEONBUK)
            code(8321094)
        }
    }

    // SchoolContext 생성
    val context = client.schoolContext("우석고")

    val asContext = school?.asContext(client)
}
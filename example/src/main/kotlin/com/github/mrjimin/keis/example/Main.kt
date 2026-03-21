package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.KeisConfig
import com.github.mrjimin.keis.api.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val client = KeisClient(
        KeisConfig(key = "YOUR_API_KEY")
    )

    // 기본 검색 (여러 개 나올 수 있음)
    val schools = client.schoolInfo("전주고")
    schools.forEach { println(it.schoolName) } // 전주고등학교, 전주고등학교부설방송통신고등학교

    // 정확한 학교 조회 (Null)
    val schoolOrNull = client.schoolInfoExactOrNull("전주고")
    if (schoolOrNull != null) {
        println("찾음: ${schoolOrNull.schoolName}") // 전주고등학교
    } else {
        println("학교 없음")
    }

    // 정확한 학교 조회 (예외)
    try {
        val school = client.schoolInfoExact("우석고등학교")
        println("찾음: ${school.schoolName}") // 우석고등학교
    } catch (_: Exception) {
        println("못 찾음")
    }

    // 여러 개 동시에 조회 (빠름)
    val multipleSchools = client.schoolInfoMultiple(
        listOf(
            "우석고등학교",
            "서울고등학교",
            "부산고등학교"
        )
    )

    multipleSchools.forEach {
        println(it.schoolName)
    }
    /*
    우석고등학교
    서울고등학교
    부산고등학교
     */

}
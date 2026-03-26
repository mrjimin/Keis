package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.*

//suspend fun main() {
//    val client = KeisClient("YOUR_API_KEY")
//
//    // 기본 검색 (여러 개 나올 수 있음)
//    val schools = client.schoolInfo("전주고")
//    schools.forEach { println(it.schoolName) } // 전주고등학교, 전주고등학교부설방송통신고등학교
//
//    // 정확한 학교 조회 (예외)
//    val school = client.schoolInfoExact("우석고등학교")
//    println(school?.let { "찾음: ${it.schoolName}" }) // 우석고등학교
//}
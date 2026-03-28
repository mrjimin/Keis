package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.schoolContext
import com.github.mrjimin.keis.api.schools
import com.github.mrjimin.keis.api.school

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")

    // 여러 학교 조회
    val schools = client.schools("전주고")
    schools.forEach { println(it.name) }

    // 단일 학교 조회
    val school = client.school("우석고")
    println(school?.name)

    // SchoolContext 변환
    val schoolContext = client.schoolContext("우석고")
}
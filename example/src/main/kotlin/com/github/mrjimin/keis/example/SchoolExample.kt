package com.github.mrjimin.keis.example

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.school
import com.github.mrjimin.keis.api.schools

suspend fun main() {
    val client = KeisClient("YOUR_API_KEY")

    // 여러 학교 조회
    val schools = client.schools("전주고")
    schools.forEach {
        println(it.name)
    }

    // 단일 학교 조회
    val school = client.school("우석고등학교")
    println(school?.let { "찾음: ${it.name}" })
}
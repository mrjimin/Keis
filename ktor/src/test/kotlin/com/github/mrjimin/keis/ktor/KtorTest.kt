package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.api.school.school
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class KtorTest {

    @Test
    fun ktor_v2(): Unit = runBlocking {

        val key =
            Dotenv.configure()
                .directory("../")
                .load()
                .get("YOUR_API_KEY")

        val client = keisKtor(key)

        val school = client.school("우석고")!!
        println(school)

//        val meal = client.meals {
//            school(school)
//
//            thisWeek()
//
//            dinner()
//        }
//
//        println(meal)
//
//        val timetable = client.timetables(school) {
//            grade(2)
//            classNumber(5)
//
//            date(LocalDate.of(2026, 6, 29))
//            fill()
//        }
//
//        println(timetable)
//
//        timetable.forEach {
//            println("${it.period} - ${it.content ?: "공강"}")
//        }
//    }
    }
}
package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.api.school.schoolContext
import com.github.mrjimin.keis.core.enums.MealType
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.time.LocalDate

class KtorTest {

    @Test
    fun ktor() = runBlocking  {
        val key = Dotenv.configure().directory("../").load().get("YOUR_API_KEY")
        val client = keisKtor(key)
        val context = client.schoolContext("우석고") ?: return@runBlocking
        println(context.school)
        println()

        context.schedules {
            dateRange {
                thisMonth()
            }
        }.forEach {
            println(it)
        }

        println("")

        context.meals {
            type(MealType.LUNCH)
            today()
        }.forEach {
            println(it)
        }

        println("")

        context.timetables {
            grade(2)
            classNumber(5)
            fillMissing()

//            today()
            dateRange {
                single(LocalDate.of(2026, 5, 8))
            }
        }.forEach {
            println("${it.period} - ${it.content ?: "공강"}")
        }
//
//        println(school?.schedules {})
    }

}
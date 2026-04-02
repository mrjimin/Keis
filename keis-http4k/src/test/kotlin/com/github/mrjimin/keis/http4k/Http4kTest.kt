package com.github.mrjimin.keis.http4k

import com.github.mrjimin.keis.core.api.school.schoolContext
import com.github.mrjimin.keis.core.enums.MealType
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import kotlin.test.Test

class Http4kTest {

    @Test
    fun http4k() = runBlocking  {
        val key = Dotenv.configure().directory("../").load()
        val client = keisHttp4k(key.get("YOUR_API_KEY"))
        val context = client.schoolContext("우석고") ?: return@runBlocking
        println(context.school)
//    println(school?.meal {
//        date {
//            today()
//        }
//        type(MealType.DINNER)
//    }!!.content )

        val specificSchedule = context.schedules {
            dateRange {
                single(LocalDate.of(2026, 4, 2))
            }
        }

        println(specificSchedule)
//
        println(context.meals {
            type(MealType.DINNER)
            dateRange {
                thisWeek()
            }
        })
//
//        println(school?.schedules {})
    }
}
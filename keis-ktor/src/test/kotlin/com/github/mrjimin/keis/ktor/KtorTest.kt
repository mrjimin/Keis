package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.api.school.schoolContext
import com.github.mrjimin.keis.core.enums.MealType
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import kotlin.test.Test

class KtorTest {

    @Test
    fun `ktor`() = runBlocking  {
        val client = keisKtor(Dotenv.load().get("YOUR_API_KEY"))
        val school = client.schoolContext("우석고")
//    println(school?.meal {
//        date {
//            today()
//        }
//        type(MealType.DINNER)
//    }!!.content )

        val specificSchedule = school?.schedules {
            dateRange {
                single(LocalDate.of(2026, 4, 2))
            }
        }

        println(specificSchedule)
//
        println(school?.meals {
            type(MealType.DINNER)
            dateRange {
                thisWeek()
            }
        })
//
//        println(school?.schedules {})
    }

}
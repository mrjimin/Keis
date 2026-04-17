package com.github.mrjimin.keis.ktor

import com.github.mrjimin.keis.core.api.school.schoolContext
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import kotlin.test.Test

class KtorTest {

    @Test
    fun ktor() = runBlocking  {
        val key = Dotenv.configure().directory("../").load()
        val client = keisKtor(key.get("YOUR_API_KEY"))
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

//    println(school?.meal {
//        date {
//            today()
//        }
//        type(MealType.DINNER)
//    }!!.content )

//        val specificSchedule = school?.schedules {
//            dateRange {
//                single(LocalDate.of(2026, 4, 2))
//            }
//        }
//
//        println(specificSchedule)
////
//        println(school?.meals {
//            type(MealType.DINNER)
//            dateRange {
//                thisWeek()
//            }
//        })

        context.timetables {
            grade(2)
            classNumber(5)
            fillMissing()

            today()
//            dateRange {
//                single(LocalDate.of(2026, 4, 17))
//            }
        }.forEach {
            println("${it.period} - ${it.content ?: "공강"}")
        }
//
//        println(school?.schedules {})
    }

}
package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.api.meal.MealQuery
import com.github.mrjimin.keis.core.api.meal.meals
import com.github.mrjimin.keis.core.api.schedule.ScheduleQuery
import com.github.mrjimin.keis.core.api.schedule.schedules
import com.github.mrjimin.keis.core.api.timetable.TimetableQuery
import com.github.mrjimin.keis.core.api.timetable.timetables
import com.github.mrjimin.keis.core.domain.School

/**
 * 특정 학교를 기준으로 조회를 수행하는 컨텍스트입니다.
 *
 * [School]을 매번 지정하지 않고도 급식, 학사일정, 시간표 등을
 * 동일한 학교 기준으로 조회할 수 있습니다.
 *
 * ```
 * val school = client.schoolContext("서울고등학교") ?: return
 *
 * val meals = school.meals {
 *     today()
 * }
 *
 * val schedules = school.schedules {
 *     thisMonth()
 * }
 * ```
 *
 * @property school 조회 기준이 되는 학교
 */
class SchoolContext(
    private val client: KeisClient,
    val school: School
) {

    /**
     * 기준 학교의 급식 정보를 조회합니다.
     *
     * @param block 추가 조회 조건을 설정하는 DSL
     */
    fun meals(
        block: MealQuery.() -> Unit = {}
    ) = client.meals(school, block)

    /**
     * 기준 학교의 학사일정 정보를 조회합니다.
     *
     * @param block 추가 조회 조건을 설정하는 DSL
     */
    fun schedules(
        block: ScheduleQuery.() -> Unit = {}
    ) = client.schedules(school, block)

    /**
     * 기준 학교의 시간표를 조회합니다.
     *
     * @param block 추가 조회 조건을 설정하는 DSL
     */
    fun timetables(
        block: TimetableQuery.() -> Unit = {}
    ) = client.timetables(school, block)
}

/**
 * 이 학교를 기준으로 하는 [SchoolContext]를 생성합니다.
 *
 * @param client 조회에 사용할 KEIS 클라이언트
 */
fun School.asContext(client: KeisClient) =
    SchoolContext(client, this)
package com.github.mrjimin.keis.core.api.schedule

import com.github.mrjimin.keis.core.domain.enums.DayNightCourse
import com.github.mrjimin.keis.core.domain.enums.SchoolCourse
import com.github.mrjimin.keis.core.query.DateQuery

/**
 * 학사일정 정보를 조회하기 위한 조건을 설정하는 DSL입니다.
 *
 * 학교, 조회 날짜 또는 기간뿐만 아니라
 * 주·야간 과정과 학교 과정 등의 조건을 지정할 수 있습니다.
 *
 * ```
 * client.schedules {
 *     school(school)
 *     thisMonth()
 *     dayNightCourse(DayNightCourse.DAY)
 * }
 * ```
 */
class ScheduleQuery : DateQuery(
    "AA_YMD",
    "AA_FROM_YMD",
    "AA_TO_YMD"
) {

    /**
     * 조회할 주·야간 과정을 설정합니다.
     *
     * @param dayNightCourse 주·야간 과정
     */
    fun dayNightCourse(dayNightCourse: DayNightCourse) {
        put("DGHT_CRSE_SC_NM", dayNightCourse.label)
    }

    /**
     * 조회할 학교 과정을 설정합니다.
     *
     * @param schoolCourse 학교 과정
     */
    fun schoolCourse(schoolCourse: SchoolCourse) {
        put("SCHUL_CRSE_SC_NM", schoolCourse.label)
    }
}
package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.query.DateQuery

/**
 * 시간표 조회 조건을 설정하는 DSL입니다.
 *
 * 조회 날짜, 학년, 반, 전공 등의 조건을 지정할 수 있으며,
 * 시간표 결과의 누락된 교시 처리 방식도 설정할 수 있습니다.
 *
 * ```
 * client.timetables {
 *     school(school)
 *     today()
 *     grade(3)
 *     classNumber(2)
 *     fill()
 * }
 * ```
 */
class TimetableQuery : DateQuery(
    "ALL_TI_YMD",
    "TI_FROM_YMD",
    "TI_TO_YMD"
) {

    /**
     * 조회 결과에서 누락된 교시를 빈 시간표로 채울지 여부입니다.
     */
    internal var fill = false

    /**
     * 하루 최대 교시 수입니다.
     *
     * [fill]이 활성화된 경우 누락된 교시를 채우는 기준으로 사용됩니다.
     */
    internal var maxPeriod = 7
        private set

    /**
     * 조회할 학년을 설정합니다.
     *
     * @param grade 학년
     */
    fun grade(grade: Int) {
        put("GRADE", grade)
    }

    /**
     * 조회할 반 번호를 설정합니다.
     *
     * @param classNumber 반 번호
     */
    fun classNumber(classNumber: Int) {
        put("CLASS_NM", classNumber)
    }

    /**
     * 조회할 전공 또는 계열을 설정합니다.
     *
     * @param major 전공명
     */
    fun major(major: String) {
        put("DDDEP_NM", major)
    }

    /**
     * 누락된 교시를 빈 시간표로 채울지 설정합니다.
     *
     * 활성화하면 조회 결과에 없는 교시도 빈 시간표 항목으로 추가됩니다.
     *
     * @param enable 활성화 여부
     */
    fun fill(enable: Boolean = true) {
        fill = enable
    }

    /**
     * 하루 최대 교시 수를 설정합니다.
     *
     * [fill]이 활성화된 경우 설정한 교시 수까지 빈 항목을 생성합니다.
     *
     * @param value 최대 교시 수
     * @throws IllegalArgumentException 값이 0 이하인 경우
     */
    fun maxPeriod(value: Int) {
        require(value > 0)
        maxPeriod = value
    }
}
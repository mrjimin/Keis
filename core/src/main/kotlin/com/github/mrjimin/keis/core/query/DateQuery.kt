package com.github.mrjimin.keis.core.query

import com.github.mrjimin.keis.core.api.school.SchoolQuery
import com.github.mrjimin.keis.core.internal.*
import java.time.LocalDate

/**
 * 날짜 또는 날짜 범위를 조건으로 사용하는 조회(Query)의 기본 클래스입니다.
 *
 * 단일 날짜 조회와 기간 조회를 모두 지원하며,
 * 문자열(`yyyyMMdd`) 또는 [LocalDate]를 사용할 수 있습니다.
 *
 * 또한 오늘, 이번 주, 이번 달, 평일 기준 날짜 등
 * 자주 사용하는 날짜 조건을 편리하게 지정할 수 있는 메서드를 제공합니다.
 *
 * @param singleKey 단일 날짜
 * @param fromKey 조회 시작일
 * @param toKey 조회 종료일
 */
abstract class DateQuery(
    private val singleKey: String,
    private val fromKey: String,
    private val toKey: String
) : SchoolQuery() {

    /**
     * 조회 날짜를 `yyyyMMdd` 형식의 문자열로 지정합니다.
     *
     * @param date 조회할 날짜
     */
    fun date(date: String) = put(singleKey, date)

    /**
     * 조회 날짜를 [LocalDate]로 지정합니다.
     *
     * @param date 조회할 날짜
     */
    fun date(date: LocalDate) = date(date.toYmd())

    /**
     * 조회 날짜를 연, 월, 일로 지정합니다.
     *
     * @param year 연도
     * @param month 월
     * @param dayOfMonth 일
     */
    fun date(year: Int, month: Int, dayOfMonth: Int) =
        date(LocalDate.of(year, month, dayOfMonth))

    /**
     * 조회 기간을 `yyyyMMdd` 형식의 문자열로 지정합니다.
     *
     * @param from 조회 시작일
     * @param to 조회 종료일
     */
    fun range(from: String, to: String) {
        put(fromKey, from)
        put(toKey, to)
    }

    /**
     * 조회 기간을 [LocalDate]로 지정합니다.
     *
     * @param from 조회 시작일
     * @param to 조회 종료일
     */
    fun range(from: LocalDate, to: LocalDate) =
        range(from.toYmd(), to.toYmd())

    /**
     * 조회 날짜를 오늘로 설정합니다.
     */
    fun today() = date(now())

    /**
     * 조회 날짜를 어제로 설정합니다.
     */
    fun yesterday() = date(now().minusDays(1))

    /**
     * 조회 날짜를 내일로 설정합니다.
     */
    fun tomorrow() = date(now().plusDays(1))

    /**
     * 조회 날짜를 오늘 기준 [days]일 전으로 설정합니다.
     *
     * @param days 이전 날짜 수
     */
    fun daysAgo(days: Long) = date(now().minusDays(days))

    /**
     * 조회 날짜를 오늘 기준 [days]일 후로 설정합니다.
     *
     * @param days 이후 날짜 수
     */
    fun daysLater(days: Long) = date(now().plusDays(days))

    /**
     * 오늘이 주말인 경우 가장 가까운 평일로 보정하여 조회 날짜를 설정합니다.
     */
    fun weekdayToday() = date(now().adjustToWeekday())

    /**
     * 이번 평일 주(월~금)를 조회 기간으로 설정합니다.
     */
    fun thisWeekdayWeek() = range(startOfWeek(), endOfWeekdayWeek())

    /**
     * 지난 평일 주(월~금)를 조회 기간으로 설정합니다.
     */
    fun lastWeekdayWeek() =
        range(startOfWeek().minusWeeks(1), endOfWeekdayWeek().minusWeeks(1))

    /**
     * 다음 평일 주(월~금)를 조회 기간으로 설정합니다.
     */
    fun nextWeekdayWeek() =
        range(startOfWeek().plusWeeks(1), endOfWeekdayWeek().plusWeeks(1))

    /**
     * 오늘을 기준으로 [days]번째 이전 평일을 조회 날짜로 설정합니다.
     *
     * 주말은 계산에서 제외됩니다.
     *
     * 예를 들어 월요일에 `weekdayDaysAgo(1)`을 호출하면
     * 이전 금요일이 설정됩니다.
     *
     * @param days 이전 평일 수
     */
    fun weekdayDaysAgo(days: Int) {
        var result = now()
        var counted = 0
        while (counted < days) {
            result = result.minusDays(1)
            if (result.isWeekday) counted++
        }
        date(result)
    }

    /**
     * 이번 주를 조회 기간으로 설정합니다.
     */
    fun thisWeek() = range(startOfWeek(), endOfWeek())

    /**
     * 지난 주를 조회 기간으로 설정합니다.
     */
    fun lastWeek() =
        range(startOfWeek().minusWeeks(1), endOfWeek().minusWeeks(1))

    /**
     * 다음 주를 조회 기간으로 설정합니다.
     */
    fun nextWeek() =
        range(startOfWeek().plusWeeks(1), endOfWeek().plusWeeks(1))

    /**
     * 이번 달 전체를 조회 기간으로 설정합니다.
     */
    fun thisMonth() = range(startOfMonth(), endOfMonth())

    /**
     * 지난 달 전체를 조회 기간으로 설정합니다.
     */
    fun lastMonth() =
        now().minusMonths(1).let { range(startOfMonth(), endOfMonth()) }

    /**
     * 다음 달 전체를 조회 기간으로 설정합니다.
     */
    fun nextMonth() =
        now().plusMonths(1).let { range(startOfMonth(), endOfMonth()) }

    /**
     * 올해 전체를 조회 기간으로 설정합니다.
     */
    fun thisYear() = range(startOfYear(), endOfYear())
}
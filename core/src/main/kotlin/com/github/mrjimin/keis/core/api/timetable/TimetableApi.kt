package com.github.mrjimin.keis.core.api.timetable

import com.github.mrjimin.keis.core.KeisClient
import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.Timetable
import com.github.mrjimin.keis.core.domain.enums.SchoolType
import com.github.mrjimin.keis.core.endpoint.TimetableEndpoint

/**
 * 시간표를 조회합니다.
 *
 * [TimetableQuery]를 통해 학교, 날짜, 학년, 반 등의 조회 조건을 지정할 수 있습니다.
 *
 * [schoolType]에 따라 알맞은 시간표 API 엔드포인트를 사용하며,
 * 해당 학교급의 기본 최대 교시 수가 자동으로 적용됩니다.
 *
 * 조회 조건에서 `fill` 옵션을 활성화하면 조회되지 않은 교시도
 * 빈 시간표 항목으로 채워 반환합니다.
 *
 * @param schoolType 조회할 학교의 학교급
 * @param block 시간표 조회 조건을 설정하는 DSL
 * @return 조회된 시간표 목록
 */
suspend fun KeisClient.timetables(
    schoolType: SchoolType,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> {

    val rawList = fetch(
        TimetableEndpoint(schoolType),
        {
            TimetableQuery().apply {
                maxPeriod(schoolType.defaultMaxPeriod)
            }
        },
        block
    )

    val options = TimetableQuery().apply(block)

    return if (options.fill) {
        rawList.fill(options.maxPeriod)
    } else {
        rawList
    }
}

/**
 * 지정한 학교의 시간표를 조회합니다.
 *
 * 학교의 학교급([School.type])을 기준으로 적절한 시간표 API를 사용하며,
 * 학교 정보는 조회 조건에 자동으로 추가됩니다.
 *
 * @param school 조회할 학교
 * @param block 추가 시간표 조회 조건을 설정하는 DSL
 * @return 조회된 시간표 목록
 */
suspend fun KeisClient.timetables(
    school: School,
    block: TimetableQuery.() -> Unit = {}
): List<Timetable> = timetables(school.type) {
    school(school)
    block()
}

/**
 * 날짜별 시간표에서 누락된 교시를 빈 시간표 항목으로 채웁니다.
 *
 * 일부 교시 정보가 API 응답에 포함되지 않은 경우에도
 * 일정한 교시 개수를 유지할 수 있도록 빈 [Timetable]을 생성합니다.
 *
 * @param maxPeriod 하루 최대 교시 수
 * @return 누락된 교시가 채워진 시간표 목록
 */
private fun List<Timetable>.fill(maxPeriod: Int): List<Timetable> =
    groupBy(Timetable::date)
        .flatMap { (_, dayList) ->
            val template = dayList.firstOrNull() ?: return@flatMap emptyList()
            val periodMap = dayList.associateBy(Timetable::period)

            (1..maxPeriod).map { period ->
                periodMap[period] ?: template.toEmpty(period)
            }
        }

/**
 * 지정한 교시에 대한 빈 시간표 항목을 생성합니다.
 *
 * 기존 시간표의 날짜, 학교 정보 등 기본 정보는 유지하고,
 * 교시와 수업 관련 정보만 초기화합니다.
 *
 * @param period 생성할 교시
 * @return 비어 있는 시간표 항목
 */
private fun Timetable.toEmpty(period: Int): Timetable = copy(
    period = period,
    order = null,
    major = null,
    classroom = null,
    content = null
)
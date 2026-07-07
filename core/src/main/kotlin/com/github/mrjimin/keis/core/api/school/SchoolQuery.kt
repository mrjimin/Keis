package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.query.Query

/**
 * 학교 조회 조건을 설정하는 DSL입니다.
 *
 * 학교명 또는 교육청과 학교 코드를 이용하여 조회 대상을 지정할 수 있습니다.
 *
 * 이 클래스는 학교 조회뿐만 아니라 급식, 학사일정, 시간표 등
 * 학교를 기준으로 하는 다른 조회 DSL의 기반으로도 사용됩니다.
 */
open class SchoolQuery : Query() {

    /**
     * 교육청과 학교 코드를 기준으로 조회 대상을 설정합니다.
     *
     * @param office 교육청
     * @param code 학교 코드
     */
    fun school(office: EducationOffice, code: Int) {
        put("ATPT_OFCDC_SC_CODE", office.code)
        put("SD_SCHUL_CODE", code)
    }

    /**
     * 조회 대상을 지정한 학교로 설정합니다.
     *
     * @param school 학교 정보
     */
    fun school(school: School) {
        school(school.office, school.code)
    }

    /**
     * 학교명을 기준으로 조회 대상을 설정합니다.
     *
     * @param name 학교명
     */
    fun schoolName(name: String) {
        put("SCHUL_NM", name)
    }

    /**
     * 학교 조회 조건을 그룹화하여 설정합니다.
     *
     * DSL 내부에서 여러 학교 관련 조건을 함께 지정할 때 사용할 수 있습니다.
     *
     * @param block 학교 조회 조건을 설정하는 DSL
     */
    fun school(block: SchoolQuery.() -> Unit) {
        apply(block)
    }
}
package com.github.mrjimin.keis.core.api.school

import com.github.mrjimin.keis.core.domain.School
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.query.Query

open class SchoolQuery : Query() {

    fun school(office: EducationOffice, code: Int) {
        put("ATPT_OFCDC_SC_CODE", office.code)
        put("SD_SCHUL_CODE", code)
    }

    fun school(school: School) {
        school(school.office, school.code)
    }

    fun schoolName(name: String) {
        put("SCHUL_NM", name)
    }

    fun school(block: SchoolQuery.() -> Unit) {
        apply(block)
    }
}
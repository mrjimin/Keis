package com.github.mrjimin.keis.api.school

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.internal.dsl.Builder
import com.github.mrjimin.keis.internal.dsl.KeisDsl

@KeisDsl
class SchoolQueryBuilder: Builder<SchoolQuery> {
    private var office: EducationOffice? = null
    private var schoolName: String? = null
    private var schoolCode: Int? = null

    override var pIndex: Int = 1
    override var pSize: Int = 100
    override var stats: Boolean = false

    fun office(office: EducationOffice) {
        this.office = office
    }

    fun name(name: String) {
        this.schoolName = name
    }

    fun code(code: Int) {
        this.schoolCode = code
    }

    override fun build(): SchoolQuery = SchoolQuery(
        office,
        schoolName,
        schoolCode,
        pIndex,
        pSize,
        stats
    )
}
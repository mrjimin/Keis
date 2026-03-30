package com.github.mrjimin.keis.api.dsl.builder

import com.github.mrjimin.keis.api.dsl.KeisDsl
import com.github.mrjimin.keis.api.dsl.query.SchoolQuery
import com.github.mrjimin.keis.enums.EducationOffice

@KeisDsl
class SchoolQueryBuilder {
    private var office: EducationOffice? = null
    private var schoolName: String? = null
    private var schoolCode: Int? = null

    fun office(office: EducationOffice) {
        this.office = office
    }

    fun name(name: String) {
        this.schoolName = name
    }

    fun code(code: Int) {
        this.schoolCode = code
    }

    fun build(): SchoolQuery = SchoolQuery(
        office,
        schoolName,
        schoolCode
    )
}
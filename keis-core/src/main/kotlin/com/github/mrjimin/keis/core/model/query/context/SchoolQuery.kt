package com.github.mrjimin.keis.core.model.query.context

import com.github.mrjimin.keis.core.enums.EducationOffice
import com.github.mrjimin.keis.core.model.domain.School
import com.github.mrjimin.keis.core.model.query.QueryContext

interface SchoolQuery : QueryContext {

    fun school(block: Builder.() -> Unit) = apply {
        Builder(this).apply(block)
    }

    fun school(school: School) = apply {
        put("ATPT_OFCDC_SC_CODE", school.office.code)
        put("SD_SCHUL_CODE", school.code)
    }

    fun school(office: EducationOffice, code: Int) = apply {
        put("ATPT_OFCDC_SC_CODE", office.code)
        put("SD_SCHUL_CODE", code)
    }

    fun name(name: String) = apply {
        put("SCHUL_NM", name)
    }

    class Builder(
        private val ctx: QueryContext
    ) {

        fun from(school: School) = apply {
            ctx.put("ATPT_OFCDC_SC_CODE", school.office.code)
            ctx.put("SD_SCHUL_CODE", school.code)
        }

        fun office(office: EducationOffice) = apply {
            ctx.put("ATPT_OFCDC_SC_CODE", office.code)
        }

        fun code(code: Int) = apply {
            ctx.put("SD_SCHUL_CODE", code)
        }

        fun name(name: String) = apply {
            ctx.put("SCHUL_NM", name)
        }
    }
}
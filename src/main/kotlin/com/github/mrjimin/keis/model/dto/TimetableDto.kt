package com.github.mrjimin.keis.model.dto

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.internal.dateFormat
import com.github.mrjimin.keis.model.domain.Timetable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class TimetableDto(
    @SerialName("ATPT_OFCDC_SC_CODE")
    val officeCode: String,

    @SerialName("ATPT_OFCDC_SC_NM")
    val officeName: String,

    @SerialName("SD_SCHUL_CODE")
    val schoolCode: String,

    @SerialName("SCHUL_NM")
    val schoolName: String,

    @SerialName("GRADE")
    val grade: Int,

    @SerialName("CLASS_NM")
    val classNumber: Int,

    @SerialName("PERIO")
    val period: Int,

    @SerialName("AY")
    val year: Int,

    @SerialName("SEM")
    val semester: Int,

    @SerialName("ALL_TI_YMD")
    private val dateText: String,

    @SerialName("ORD_SC_NM")
    val order: String? = null,

    @SerialName("DDDEP_NM")
    val major: String? = null,

    @SerialName("CLRM_NM")
    val classroom: String? = null,

    @SerialName("ITRT_CNTNT")
    val content: String? = null
) {

    fun toDomain(): Timetable = Timetable(
        EducationOffice.from(officeCode, officeName),
        schoolCode,
        schoolName,
        grade,
        classNumber,
        period,
        year,
        semester,
        LocalDate.from(dateFormat.parse(dateText)),
        order,
        major,
        classroom,
        content
    )
}
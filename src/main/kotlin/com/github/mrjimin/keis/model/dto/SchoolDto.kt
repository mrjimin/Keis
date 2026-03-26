package com.github.mrjimin.keis.model.dto

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.model.domain.School
import kotlinx.serialization.*

@Serializable
data class SchoolDto(
    @SerialName("ATPT_OFCDC_SC_CODE")
    private val officeCode: String,

    @SerialName("ATPT_OFCDC_SC_NM")
    private val officeName: String,

    @SerialName("SD_SCHUL_CODE")
    val code: String,

    @SerialName("SCHUL_NM")
    val name: String,

    @SerialName("ENG_SCHUL_NM")
    val nameEn: String?,

    @SerialName("SCHUL_KND_SC_NM")
    private val typeText: String,

    @SerialName("ORG_RDNMA")
    val location: String,

    @SerialName("HS_GNRL_BUSNS_SC_NM")
    val businessType: String?,

    @SerialName("HMPG_ADRES")
    val homepageUrl: String
) {
    fun toDomain(): School = School(
        EducationOffice.from(officeCode, officeName),
        code,
        name,
        nameEn,
        SchoolType.from(typeText),
        location,
        businessType,
        homepageUrl,
    )
}
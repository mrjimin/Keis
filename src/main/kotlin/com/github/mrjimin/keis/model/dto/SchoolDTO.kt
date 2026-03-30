package com.github.mrjimin.keis.model.dto

import com.github.mrjimin.keis.enums.BusinessType
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.SchoolGender
import com.github.mrjimin.keis.enums.SchoolType
import com.github.mrjimin.keis.model.domain.School
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolDTO(
    @SerialName("ATPT_OFCDC_SC_CODE")
    val officeCode: String,

    @SerialName("ATPT_OFCDC_SC_NM")
    val officeName: String,

    @SerialName("SD_SCHUL_CODE")
    val code: Int,

    @SerialName("SCHUL_NM")
    val name: String,

    @SerialName("ENG_SCHUL_NM")
    val nameEn: String?,

    @SerialName("SCHUL_KND_SC_NM")
    val typeText: String,

    @SerialName("COEDU_SC_NM")
    val genderText: String,

    @SerialName("ORG_RDNMA")
    val location: String,

    @SerialName("HS_GNRL_BUSNS_SC_NM")
    val businessTypeText: String?,

    @SerialName("HMPG_ADRES")
    val homepageUrl: String
) {
    fun toDomain(): School = School(
        EducationOffice.from(officeCode, officeName),
        code,
        name,
        nameEn,
        SchoolType.from(typeText),
        SchoolGender.from(genderText),
        location,
        BusinessType.from(businessTypeText),
        homepageUrl,
    )
}
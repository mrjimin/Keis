package com.github.mrjimin.keis.core.internal.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolResponse(
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

    @SerialName("FOND_SC_NM")
    val foundationTypeText: String,

    @SerialName("COEDU_SC_NM")
    val genderText: String,

    @SerialName("ORG_RDNMA")
    val location: String,

    @SerialName("HS_GNRL_BUSNS_SC_NM")
    val businessTypeText: String?,

    @SerialName("HMPG_ADRES")
    val homepageUrl: String,

    @SerialName("DGHT_SC_NM")
    val dayNightCourseText: String,

    @SerialName("FOND_YMD")
    val establishedDateText: String,

    @SerialName("LOAD_DTM")
    val loadDateTime: String
)
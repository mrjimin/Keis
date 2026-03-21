package com.github.mrjimin.keis.dto

import com.github.mrjimin.keis.KeisRowContainer
import com.github.mrjimin.keis.KeisWrapper
import com.github.mrjimin.keis.enums.SchoolType
import kotlinx.serialization.*

@Serializable
data class SchoolInfoResponse(
    @SerialName("schoolInfo")
    override val items: List<KeisWrapper<SchoolInfoRow>>
) : KeisRowContainer<SchoolInfoRow>

@Serializable
data class SchoolInfoRow(
    @SerialName("ATPT_OFCDC_SC_CODE")
    val officeCode: String,

    @SerialName("ATPT_OFCDC_SC_NM")
    val officeName: String,

    @SerialName("SCHUL_NM")
    val schoolName: String,

    @SerialName("SD_SCHUL_CODE")
    val schoolCode: String,

    @SerialName("SCHUL_KND_SC_NM")
    val schoolTypeText: String,

    @SerialName("ORG_RDNMA")
    val location: String,

    @SerialName("HMPG_ADRES")
    val homepage: String,

    @SerialName("HS_GNRL_BUSNS_SC_NM")
    val businessType: String?,
) {
    @Transient
    val schoolType: SchoolType = SchoolType.from(schoolTypeText)
}
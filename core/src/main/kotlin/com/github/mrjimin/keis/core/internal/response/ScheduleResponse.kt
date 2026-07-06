package com.github.mrjimin.keis.core.internal.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    @SerialName("ATPT_OFCDC_SC_CODE")
    val officeCode: String,

    @SerialName("ATPT_OFCDC_SC_NM")
    val officeName: String,

    @SerialName("SD_SCHUL_CODE")
    val schoolCode: Int,

    @SerialName("SCHUL_NM")
    val schoolName: String,

    @SerialName("AA_YMD")
    val dateText: String,

    @SerialName("EVENT_NM")
    val eventName: String,

    @SerialName("EVENT_CNTNT")
    val eventContent: String?,

    @SerialName("ONE_GRADE_EVENT_YN")
    val grade1: String?,

    @SerialName("TW_GRADE_EVENT_YN")
    val grade2: String?,

    @SerialName("THREE_GRADE_EVENT_YN")
    val grade3: String?,

    @SerialName("FR_GRADE_EVENT_YN")
    val grade4: String?,

    @SerialName("FIV_GRADE_EVENT_YN")
    val grade5: String?,

    @SerialName("SIX_GRADE_EVENT_YN")
    val grade6: String?,

    @SerialName("LOAD_DTM")
    val loadDateTime: String
)
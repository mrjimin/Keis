package com.github.mrjimin.keis.core.model.dto

import com.github.mrjimin.keis.core.enums.EducationOffice
import com.github.mrjimin.keis.core.internal.toLocalDate
import com.github.mrjimin.keis.core.model.domain.Meal
import com.github.mrjimin.keis.core.model.domain.Schedule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDTO(
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
    val grade6: String?
): DomainConverter<Schedule> {

    override fun toDomain(): Schedule = Schedule(
        EducationOffice.from(officeCode, officeName),
        schoolCode,
        schoolName,
        dateText.toLocalDate(),
        eventName,
        eventContent,
        buildList {
            if (grade1 == "Y") add(1)
            if (grade2 == "Y") add(2)
            if (grade3 == "Y") add(3)
            if (grade4 == "Y") add(4)
            if (grade5 == "Y") add(5)
            if (grade6 == "Y") add(6)
        }
    )
}
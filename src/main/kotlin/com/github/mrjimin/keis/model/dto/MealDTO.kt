package com.github.mrjimin.keis.model.dto

import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.MealType
import com.github.mrjimin.keis.internal.toLocalDate
import com.github.mrjimin.keis.model.domain.Meal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDTO(
    @SerialName("ATPT_OFCDC_SC_CODE")
    val officeCode: String,

    @SerialName("ATPT_OFCDC_SC_NM")
    val officeName: String,

    @SerialName("SD_SCHUL_CODE")
    val schoolCode: String,

    @SerialName("SCHUL_NM")
    val schoolName: String,

    @SerialName("MMEAL_SC_NM")
    val mealTypeText: String,

    @SerialName("MLSV_YMD")
    val dateText: String,

    @SerialName("MLSV_FGR")
    val mealCountText: Double,

    @SerialName("DDISH_NM")
    val content: String,

    @SerialName("ORPLC_INFO")
    val origin: String,

    @SerialName("NTR_INFO")
    val nutrition: String,

    @SerialName("CAL_INFO")
    val caloriesText: String
) {
    fun toDomain(): Meal =Meal(
        EducationOffice.from(officeCode, officeName),
        schoolCode,
        schoolName,
        MealType.from(mealTypeText),
        dateText.toLocalDate(),
        mealCountText.toInt(),
        content.replace("<br/>", "\n"),
        origin.replace("<br/>", "\n"),
        nutrition.replace("<br/>", "\n"),
        caloriesText.removeSuffix(" Kcal").toDouble()
    )
}
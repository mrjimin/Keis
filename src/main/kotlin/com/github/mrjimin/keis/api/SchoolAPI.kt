package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.dto.SchoolInfo
import com.github.mrjimin.keis.dto.SchoolInfoResponse
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.enums.KeisAPI
import io.ktor.client.request.*

private suspend inline fun KeisClient.schoolInfo(
    builder: HttpRequestBuilder.() -> Unit
): List<SchoolInfo> {
    return request<SchoolInfoResponse>(KeisAPI.SCHOOL_INFO) {
        builder()
    }.rows
}

suspend fun KeisClient.schoolInfo(schoolName: String): List<SchoolInfo> {
    return schoolInfo {
        parameter("SCHUL_NM", schoolName)
    }
}

suspend fun KeisClient.schoolInfoExact(schoolName: String): SchoolInfo? {
    return schoolInfo(schoolName).firstOrNull()
}

suspend fun KeisClient.schoolInfoByCode(educationOffice: EducationOffice, schoolCode: Int): SchoolInfo? {
    return schoolInfo {
        parameter("ATPT_OFCDC_SC_CODE", educationOffice.code)
        parameter("SD_SCHUL_CODE", schoolCode)
    }.firstOrNull()
}

/**
 * 여러 학교 조회
 * @param schoolNames 학교 이름
 */
//@Deprecated("미사용")
//suspend fun KeisClient.schoolInfoMultiple(schoolNames: List<String>): List<SchoolInfo> =
//    coroutineScope {
//        schoolNames.map { async { schoolInfoExactOrNull(it) } }
//            .awaitAll()
//            .filterNotNull()
//    }

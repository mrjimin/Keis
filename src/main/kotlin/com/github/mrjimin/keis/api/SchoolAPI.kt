package com.github.mrjimin.keis.api

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.dto.SchoolInfoResponse
import com.github.mrjimin.keis.dto.SchoolInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import io.ktor.client.request.parameter

/**
 * 학교 이름으로 조회
 * @param schoolName 학교 이름
 * @param takeFirst true: 첫 번째만 반환, false: 전체 반환 (되도록 true 사용)
 */
suspend fun KeisClient.schoolInfo(
    schoolName: String,
    takeFirst: Boolean = false
): List<SchoolInfo> {
    val rows = request<SchoolInfoResponse>("schoolInfo") {
        parameter("SCHUL_NM", schoolName)
    }.rows

    return if (takeFirst) rows.take(1) else rows
}

/**
 * 정확한 학교 조회 (없으면 Null)
 * @param schoolName 학교 이름
 */
suspend fun KeisClient.schoolInfoExactOrNull(schoolName: String): SchoolInfo? =
    schoolInfo(schoolName, takeFirst = true).firstOrNull()

/**
 * 정확한 학교 조회 (없으면 예외)
 * @param schoolName 학교 이름
 */
suspend fun KeisClient.schoolInfoExact(schoolName: String): SchoolInfo =
    schoolInfoExactOrNull(schoolName)
        ?: throw NoSuchElementException("School not found: $schoolName")

/**
 * 여러 학교 조회
 * @param schoolNames 학교 이름
 */
suspend fun KeisClient.schoolInfoMultiple(schoolNames: List<String>): List<SchoolInfo> =
    coroutineScope {
        schoolNames.map { async { schoolInfoExactOrNull(it) } }
            .awaitAll()
            .filterNotNull()
    }

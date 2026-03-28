package com.github.mrjimin.keis.service

import com.github.mrjimin.keis.KeisClient
import com.github.mrjimin.keis.api.*
import com.github.mrjimin.keis.enums.EducationOffice
import com.github.mrjimin.keis.model.domain.School

class SchoolService(private val client: KeisClient) {

    suspend fun findSchool(schoolName: String): List<School> =
        client.school(schoolName)

    suspend fun findSchoolOne(schoolName: String): School? =
        client.schoolOne(schoolName)

    suspend fun findSchoolByCode(educationOffice: EducationOffice, schoolCode: Int): School? =
        client.schoolByCode(educationOffice, schoolCode)

}
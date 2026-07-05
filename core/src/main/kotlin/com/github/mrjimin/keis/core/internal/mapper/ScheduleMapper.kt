package com.github.mrjimin.keis.core.internal.mapper

import com.github.mrjimin.keis.core.domain.Schedule
import com.github.mrjimin.keis.core.domain.enums.EducationOffice
import com.github.mrjimin.keis.core.internal.response.ScheduleResponse
import com.github.mrjimin.keis.core.internal.toLocalDate

internal fun ScheduleResponse.toDomain(): Schedule =
    Schedule(
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
        },
        loadDateTime.toLocalDate()
    )
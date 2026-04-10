package com.github.mrjimin.keis.core.model.dto

interface DomainConverter<T> {
    fun toDomain(): T
}
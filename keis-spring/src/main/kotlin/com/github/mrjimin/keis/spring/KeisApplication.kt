package com.github.mrjimin.keis.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KeisApplication

fun main(args: Array<String>) {
    runApplication<KeisApplication>(*args)
}
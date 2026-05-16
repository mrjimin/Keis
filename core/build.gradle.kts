plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.serialization)
}

group = "com.github.mrjimin.keis.core"
version = parent!!.version

dependencies {
    api(libs.kotlinx.serialization.json)
}
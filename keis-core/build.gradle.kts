plugins {
    kotlin("jvm")
    alias(libs.plugins.kotlin.serialization)
}

group = "com.github.mrjimin.keis.core"
version = parent!!.version

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
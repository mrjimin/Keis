plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.ktor"
version = parent!!.version

dependencies {
    implementation(project(":keis-core"))

    api(libs.ktor.client.core)
    api(libs.ktor.client.cio)
    api(libs.ktor.client.content.negotiation)
    api(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback.classic)
}
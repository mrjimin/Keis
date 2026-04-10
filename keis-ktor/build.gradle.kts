plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.ktor"
version = parent!!.version

dependencies {
    api(project(":keis-core"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    compileOnly(libs.logback.classic)
}
plugins {
    kotlin("jvm")
    alias(libs.plugins.ktor)
}

group = "com.github.mrjimin.keis.ktor"
version = parent!!.version

dependencies {
    implementation(project(":keis-core"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback.classic)
}

tasks.configureEach {
    if (name.contains("shadow", ignoreCase = true)) {
        enabled = false
    }
}
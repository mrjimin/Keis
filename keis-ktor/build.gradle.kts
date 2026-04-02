plugins {
    kotlin("jvm")
    id("com.gradleup.shadow") version "9.4.1"
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

tasks.named("shadowJar") {
    enabled = false
}
plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.okhttp.jvm)

    compileOnly(libs.logback.classic)
}
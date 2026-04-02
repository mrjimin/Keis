plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":keis-core"))
    implementation(project(":keis-ktor"))
    implementation(project(":keis-http4k"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

kotlin {
    jvmToolchain(21)
}
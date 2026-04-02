plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.ktor"
version = parent!!.version

dependencies {
    implementation(project(":keis-core"))

    implementation("io.ktor:ktor-client-core:3.4.2")
    implementation("io.ktor:ktor-client-cio:3.4.2")
    implementation("io.ktor:ktor-client-content-negotiation:3.4.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.2")
    implementation("ch.qos.logback:logback-classic:1.5.32")
}
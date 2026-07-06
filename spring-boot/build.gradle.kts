plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))

    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.webflux)

    // local
    implementation(libs.kotlinx.coroutines.core)
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.3.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}

tasks.test {
    jvmArgs = listOf(
        "--enable-native-access=ALL-UNNAMED",
    )
    testLogging {
        showStandardStreams = true
        events("passed", "failed", "skipped")
    }
}

tasks.jar {
    enabled = true
}
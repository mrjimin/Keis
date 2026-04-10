plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.spring"
version = parent!!.version

dependencies {
    api(project(":keis-core"))

    compileOnly(libs.spring.boot.starter.webmvc)
    compileOnly(libs.spring.boot.starter.webflux)

    // local
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.10.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.3.20")
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
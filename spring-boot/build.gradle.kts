plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.spring"
version = parent!!.version

dependencies {
    compileOnly(libs.spring.boot.starter.webmvc)
    compileOnly(libs.spring.boot.starter.webflux)

    // local
    implementation(libs.kotlinx.coroutines.core)
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

tasks.jar {
    enabled = true
}
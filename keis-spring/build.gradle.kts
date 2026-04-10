plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.spring"
version = parent!!.version

dependencies {
    implementation(project(":keis-core"))

    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.webflux)

    // local
    implementation("io.netty:netty-resolver-dns-native-macos:4.2.12.Final:osx-aarch_64")
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
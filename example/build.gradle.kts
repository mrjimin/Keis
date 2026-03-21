plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(project(":"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

application {
    mainClass = "com.github.mrjimin.keis.example.MainKt"
}

kotlin {
    jvmToolchain(21)
}
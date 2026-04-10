plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":keis-core"))
    implementation(project(":keis-ktor"))
    implementation(project(":keis-http4k"))
    implementation(project(":keis-spring-boot"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}

tasks.withType<PublishToMavenLocal>().configureEach {
    enabled = false
}
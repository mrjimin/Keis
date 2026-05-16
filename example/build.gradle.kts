plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ktor"))
    implementation(project(":http4k"))
    implementation(project(":spring-boot"))
    implementation(libs.kotlinx.coroutines.core)
}

tasks.withType<PublishToMavenLocal>().configureEach {
    enabled = false
}
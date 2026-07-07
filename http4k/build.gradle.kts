plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":core"))

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.http4k.core)
    implementation(libs.http4k.okhttp.client)
}
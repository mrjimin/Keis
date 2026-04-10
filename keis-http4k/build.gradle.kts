plugins {
    kotlin("jvm")
}

group = "com.github.mrjimin.keis.http4k"
version = parent!!.version

dependencies {
    api(project(":keis-core"))
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.http4k.core)
    implementation(libs.http4k.okhttp.client)
}
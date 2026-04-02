plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "com.github.mrjimin.keis"
version = "2.0.0"

subprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("maven-publish")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")
        testImplementation(kotlin("test"))
    }

    kotlin {
        jvmToolchain(21)
    }

    tasks.test {
        useJUnitPlatform()
        ignoreFailures = true
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}
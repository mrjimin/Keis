plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "com.github.mrjimin.keis"
version = "2.0.2"

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
        testImplementation("org.slf4j:slf4j-nop:2.0.17")
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
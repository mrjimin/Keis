plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "com.github.mrjimin.keis"
version = "2.0.5"

allprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")
        testImplementation(kotlin("test"))
    }

    kotlin {
        jvmToolchain(25)
    }

    tasks.test {
        useJUnitPlatform()
        ignoreFailures = true
    }

}

subprojects {
    apply {
        plugin("maven-publish")
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }
}
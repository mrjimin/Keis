plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "com.github.mrjimin.keis"
version = "3.0.3"

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
        jvmToolchain(25)
    }

    tasks.test {
        useJUnitPlatform()
        ignoreFailures = true
    }

    tasks.named<JavaCompile>("compileJava") {
        dependsOn(tasks.named("compileKotlin"))
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }
}
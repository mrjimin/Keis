plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
    id("org.jetbrains.dokka") version "2.2.0"
}

group = "com.github.mrjimin.keis"
version = "3.0.5"

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("maven-publish")
        plugin("org.jetbrains.dokka")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        dokkaPlugin("org.jetbrains.dokka:mathjax-plugin")
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

dokka {
    pluginsConfiguration.html {
        customAssets.from(rootProject.file("images/keis_image.png"))
        footerMessage.set("(c) mrjimin")
    }
}
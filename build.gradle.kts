plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
    id("org.jetbrains.dokka") version "2.2.0"
}

group = "com.github.mrjimin.keis"
version = "3.0.5"

repositories {
    mavenCentral()
}

dependencies {
    dokka(project(":core"))
    dokka(project(":example"))
    dokka(project(":http4k"))
    dokka(project(":ktor"))
    dokka(project(":spring-boot"))
}

dokka {
    dokkaPublications.html {
        moduleName.set("KEIS")
        pluginsConfiguration.html {
            customAssets.from("images/keis_image.png")
            footerMessage.set("© 2026 mrjimin. All rights reserved.")
        }
    }
}

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

    dokka {
        moduleName.set(project.name)

        pluginsConfiguration.html {
            customAssets.from("images/keis_image.png")
            footerMessage.set("© 2026 mrjimin. All rights reserved.")
        }
    }
}
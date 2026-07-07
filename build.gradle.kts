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

dokka {
    moduleName.set("KEIS")

    pluginsConfiguration.html {
        customAssets.from(
            rootProject.layout.projectDirectory
                .file("images/keis_image.png")
        )

        footerMessage.set("(c) mrjimin")
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
            customAssets.from(
                rootProject.layout.projectDirectory
                    .file("images/keis_image.png")
            )

            footerMessage.set("(c) mrjimin")
        }
    }
}

tasks.register<Copy>("prepareDokka") {
    description = "Prepare Dokka HTML site"

    dependsOn(
        ":core:dokkaGenerateHtml",
        ":ktor:dokkaGenerateHtml",
        ":spring:dokkaGenerateHtml"
    )

    into(layout.buildDirectory.dir("dokka-site"))

    from(project(":core").layout.buildDirectory.dir("dokka/html")) {
        into("core")
    }

    from(project(":ktor").layout.buildDirectory.dir("dokka/html")) {
        into("ktor")
    }

    from(project(":spring").layout.buildDirectory.dir("dokka/html")) {
        into("spring")
    }

    from("docs/index.html")
}
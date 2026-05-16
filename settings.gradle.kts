rootProject.name = "Keis"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("example")
include("core")
include("http4k")
include("ktor")
include("spring-boot")
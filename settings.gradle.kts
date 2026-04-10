rootProject.name = "Keis"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("example")
include("keis-core")
include("keis-http4k")
include("keis-ktor")
include("keis-spring")
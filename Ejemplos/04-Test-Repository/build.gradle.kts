plugins {
    kotlin("jvm") version "1.9.22"
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // https://mvnrepository.com/artifact/io.mockk/mockk
    testImplementation("io.mockk:mockk:1.13.9")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
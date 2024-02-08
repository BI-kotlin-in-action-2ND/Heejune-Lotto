plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // kotest
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.6.3")
    // mock
    testImplementation("io.mockk:mockk:1.12.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}

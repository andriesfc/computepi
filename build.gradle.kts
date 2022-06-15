import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    application
}

group = "computepi"
version = "1.0-SNAPSHOT"

val javacTarget = JavaLanguageVersion.of(17)

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:3.4.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = javacTarget.asInt().toString()
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(javacTarget)
    }
}


application {
    mainClass.set("Compute_piKt")
}
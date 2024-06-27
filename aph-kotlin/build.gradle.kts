plugins {
    `kotlin-dsl`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
}

group = "io.github.aaron-harris.gradle-conventions"

// Used for local development only; published versions should be overridden in CI/CD
version = "local-SNAPSHOT"

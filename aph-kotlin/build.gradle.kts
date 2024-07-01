import com.diffplug.gradle.spotless.BaseKotlinExtension

plugins {
    `kotlin-dsl`
    `maven-publish`

    alias(libs.plugins.spotless)
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

spotless {
    val sharedKtlint: BaseKotlinExtension.() -> Unit = {
        ktlint(libs.versions.ktlint.get())
            .setEditorConfigPath("$rootDir/.editorconfig")
    }

    kotlin {
        sharedKtlint()
        targetExclude("build/**/*.kt")
    }
    kotlinGradle {
        sharedKtlint()
    }
}

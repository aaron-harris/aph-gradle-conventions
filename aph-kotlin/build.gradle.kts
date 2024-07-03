import com.diffplug.gradle.spotless.BaseKotlinExtension

plugins {
    `kotlin-dsl`
    `maven-publish`

    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.kover.gradlePlugin)
}

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

detekt {
    source.setFrom(
        "src/main/kotlin",
        "build.gradle.kts",
    )
}

publishing {
    val gitHubRepoUrl: String? by project
    gitHubRepoUrl?.let { repoUrl ->
        val gitHubUser: String by project
        val gitHubToken: String by project

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri(repoUrl)
                credentials {
                    username = gitHubUser
                    password = gitHubToken
                }
            }
        }
    }
}

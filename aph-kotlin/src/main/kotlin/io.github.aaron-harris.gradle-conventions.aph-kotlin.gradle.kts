import com.diffplug.gradle.spotless.BaseKotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import io.github.aaron_harris.gradle.kotlin.AphKotlinExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("kotlin")

    id("com.diffplug.spotless")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.kotlinx.kover")
}

val aphKotlin =
    project.extensions.create<AphKotlinExtension>(AphKotlinExtension.EXTENSION_NAME).apply {
        extensions.create<AphKotlinExtension.CodeCoverage>(AphKotlinExtension.CodeCoverage.EXTENSION_NAME).apply {
            minimumRequired.convention(AphKotlinExtension.CodeCoverage.Defaults.MINIMUM_REQUIRED)
        }
    }

repositories {
    mavenCentral()
}

configure<SpotlessExtension> {
    val sharedKtlint: BaseKotlinExtension.() -> Unit = {
        ktlint("1.3.0")
            .setEditorConfigPath("$rootDir/.editorconfig")
    }

    kotlin {
        sharedKtlint()
    }
    kotlinGradle {
        sharedKtlint()
    }
}

configure<DetektExtension> {
    source.setFrom(
        "src/main/kotlin",
        "src/test/kotlin",
        "build.gradle.kts",
    )
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events(
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.FAILED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR,
        )
    }
}

configure<KoverProjectExtension> {
    reports {
        verify.rule {
            minBound(aphKotlin.codeCoverage.minimumRequired)
        }

        total {
            log.onCheck = true
            html.onCheck = true
        }
    }
}

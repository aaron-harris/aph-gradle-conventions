import com.diffplug.gradle.spotless.BaseKotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("kotlin")

    id("com.diffplug.spotless")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.kotlinx.kover")
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
    reports.total {
        log.onCheck = true
        html.onCheck = true
    }
}

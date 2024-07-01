import com.diffplug.gradle.spotless.BaseKotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("kotlin")

    id("com.diffplug.spotless")
    id("io.gitlab.arturbosch.detekt")
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

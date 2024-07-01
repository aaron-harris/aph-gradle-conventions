import com.diffplug.gradle.spotless.BaseKotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    id("kotlin")

    id("com.diffplug.spotless")
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

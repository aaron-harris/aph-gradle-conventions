package io.github.aaron_harris.gradle.kotlin

import org.gradle.api.Action
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.getByType

@DslMarker
annotation class AphKotlinGradlePluginDsl

/**
 * DSL extension for the `aph-kotlin` convention plugin.
 *
 * Example of configuration:
 *
 * ```
 * aphKotlin {
 *     codeCoverage {
 *         minimumRequired = 75
 *     }
 * }
 * ```
 */
@AphKotlinGradlePluginDsl
interface AphKotlinExtension : ExtensionAware {
    val codeCoverage: CodeCoverage get() = extensions.getByType()

    fun codeCoverage(block: Action<CodeCoverage>) {
        block.execute(codeCoverage)
    }

    /** Sub-extension of `aphKotlin` for settings related to code coverage. */
    @AphKotlinGradlePluginDsl
    interface CodeCoverage {
        /**
         * The minimum amount of code coverage (in terms of lines) that should be required.
         *
         * The `check` task will fail if this threshold is not met.
         *
         * Set to zero to effectively disable code coverage requirements.
         *
         * Defaults to [Defaults.MINIMUM_REQUIRED].
         */
        val minimumRequired: Property<Int>

        object Defaults {
            const val MINIMUM_REQUIRED = 80
        }

        companion object {
            internal const val EXTENSION_NAME = "codeCoverage"
        }
    }

    companion object {
        internal const val EXTENSION_NAME = "aphKotlin"
    }
}

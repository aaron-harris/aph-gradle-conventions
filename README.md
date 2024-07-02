# aph-gradle-conventions
Gradle convention plugins for use in my own projects

## Overview

Defines Gradle convention plugins for use in various kinds of projects; see below for a brief description of each of
them.  These are intended only for my own personal use and probably won't be of interest to others, but I intend to
maintain basic semantic versioning practices in case, anyway.

Each plugin is identified by its short name, which is also the Gradle module where it resides in this project.  To
consume the plugin externally, use this Gradle plugin identifier:

```kotlin
plugins {
    id("io.github.aaron-harris.gradle-conventions.<SHORT NAME>")
}
```

## Plugin `aph-kotlin`

A convention plugin for arbitrary Kotlin (JVM) projects.

Contains the following functionality:
 - Common configuration for things like repositories and unit tests that I expect to be configured in the same way 
   across all my Kotlin projects.
 - A plugin configuration DSL accessible in `build.gradle.kts` via the `aphKotlin` extension.
 - Automatic linting (using [Ktlint](https://pinterest.github.io/ktlint/latest/)), both for the consuming project's 
   sources and for its `build.gradle.kts` file.  Linting rules can be customized through `.editorconfig` in the usual
   way.
 - Static analysis using [Detekt](https://detekt.dev/), both for the consuming project's sources and for its
   `build.gradle.kts` file.  Rules can be customized by providing a Detekt configuration file in the usual way and in
   the default location (`config/detekt/detekt.yaml`, relative to the project's root directory).
 - Code coverage using [Kover](https://github.com/Kotlin/kotlinx-kover).  Coverage is automatically calculated and
   reported to the console as part of the `check` task, and an HTML report is also generated.  By default, a minimum of
   80% line coverage is required, but consuming projects can modify or waive this requirement using the plugin's 
   extension DSL.

Example configuration:

```kotlin
aphKotlin {
    codeCoverage { 
        minimumRequired = 75
        excludedClasses.add("foo.BarKt")
    }
}
```

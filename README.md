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
 - Automatic linting (using [Ktlint](https://pinterest.github.io/ktlint/latest/)), both for the consuming project's 
   sources and for its `build.gradle.kts` file.  Linting rules can be customized through `.editorconfig` in the usual
   way.

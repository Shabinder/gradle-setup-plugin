package com.arkivanov.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories

fun Project.setupRepositories() = repositories { setupRepos() }
fun Project.setupRepositoriesInAll() = allprojects { repositories { setupRepos() } }

fun RepositoryHandler.setupRepos() {
    // local built system
    mavenLocal()

    // remote repositories
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

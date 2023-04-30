import org.gradle.accessors.dm.LibrariesForDeps

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("maven-publish")
}

group = "com.arkivanov.gradle"
version = "0.0.1"

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    with(deps) {
        implementation(androidx.gradle.plugin)
        implementation(kotlin.compose.gradle)
        implementation(ktlint.gradle)
        implementation(mosaic.gradle)
        implementation(kotlin.kotlinGradlePlugin)
        implementation(sqldelight.gradle.plugin)
        implementation(i18n4k.gradle.plugin)
        implementation(kotlin.serialization)
        implementation(google.services)
        implementation(google.services)
        implementation(firebase.plugins.crashlytics)
    }

    compileOnly("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.15.0")
    compileOnly("org.jetbrains.kotlinx:binary-compatibility-validator::0.13.2")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")
    compileOnly(files(deps.javaClass.superclass.protectionDomain.codeSource.location))
    testImplementation(kotlin("test"))
}

gradlePlugin {
    plugins.create(project.name) {
        id = "com.arkivanov.gradle.setup"
        implementationClass = "com.arkivanov.gradle.GradleSetupPlugin"
    }
}

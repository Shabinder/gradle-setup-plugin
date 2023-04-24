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
    compileOnly(deps.kotlin.kotlinGradlePlugin)
    compileOnly(deps.androidx.gradle.plugin)
    compileOnly("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.3.1")
    compileOnly("org.jetbrains.kotlinx:binary-compatibility-validator:0.12.1")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
    testImplementation(kotlin("test"))
}

gradlePlugin {
    plugins.create(project.name) {
        id = "com.arkivanov.gradle.setup"
        implementationClass = "com.arkivanov.gradle.GradleSetupPlugin"
    }
}

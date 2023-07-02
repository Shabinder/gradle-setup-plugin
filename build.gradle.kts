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

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir(
        file("$buildDir/generated/source/buildConfig/main")
    )
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
        implementation(firebase.plugins.crashlytics)
    }
    implementation("com.github.gmazzo.buildconfig:plugin:4.0.4")
    compileOnly("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.3.1")
    compileOnly("org.jetbrains.kotlinx:binary-compatibility-validator:0.12.1")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
    compileOnly(files(deps.javaClass.superclass.protectionDomain.codeSource.location))
    implementation("androidx.benchmark:benchmark-baseline-profile-gradle-plugin:1.2.0-alpha16")
    testImplementation(kotlin("test"))
}

gradlePlugin {
    plugins.create(project.name) {
        id = "com.arkivanov.gradle.setup"
        implementationClass = "com.arkivanov.gradle.GradleSetupPlugin"
    }
}

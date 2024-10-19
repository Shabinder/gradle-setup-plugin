plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("maven-publish")
}

group = "com.arkivanov.gradle"
version = "0.0.1"

repositories {
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
    google()
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir(
        file("${layout.buildDirectory}/generated/source/buildConfig/main")
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
        implementation(moko.resources.generator)
        implementation(firebase.plugins.crashlytics)
    }
//    implementation("com.guardsquare:proguard-gradle:7.3.2")
//    implementation("land.sungbin.composeinvestigator:composeinvestigator-gradle-plugin:1.5.11-0.2.1")
    implementation("com.github.gmazzo.buildconfig:plugin:5.4.0") {
        version {
            strictly("5.4.0")
        }
    }
    implementation("io.github.izhangzhihao:plugin:1.0.3")
    compileOnly("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.15.0")
    compileOnly("org.jetbrains.kotlinx:binary-compatibility-validator:0.16.3")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.7")
    compileOnly(files(deps.javaClass.superclass.protectionDomain.codeSource.location))
    implementation("androidx.benchmark:benchmark-baseline-profile-gradle-plugin:1.3.3")
    testImplementation(kotlin("test"))


    /* Custom KTLINT Rules */
    compileOnly("com.pinterest.ktlint:ktlint-cli-ruleset-core:1.3.1")
    compileOnly("com.pinterest.ktlint:ktlint-rule-engine:1.3.1")
}

gradlePlugin {
    plugins.create(project.name) {
        id = "com.arkivanov.gradle.setup"
        implementationClass = "com.arkivanov.gradle.GradleSetupPlugin"
    }
}

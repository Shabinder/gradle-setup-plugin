package com.arkivanov.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.setupKtlint() {
    checkIsRootProject()

    allprojects {
        if (path.contains("jndcrash")) {
            return@allprojects
        }

        plugins.apply("org.jlleitschuh.gradle.ktlint")

        // Optionally configure plugin
        extensions.configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
            debug.set(false)
            outputToConsole.set(true)
            filter {
                exclude("**/generated/**")
                exclude("**/jndcrash/**")
                include("**/kotlin/**")
            }
        }

        dependencies {
            "ktlintRuleset"(project(":ktlint-ruleset"))
        }
    }
}

package com.arkivanov.gradle

import com.github.gmazzo.gradle.plugins.BuildConfigExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import kotlin.reflect.full.memberProperties

object SoundBoundConfig {
    const val APP_VERSION: String = "0.0.11"
    const val APP_VERSION_CODE: Long = 16L
}

fun Project.setupBuildConfig(
    configure: BuildConfigExtension.() -> Unit = {}
) {
    plugins.apply("com.github.gmazzo.buildconfig")
    extensions.configure<BuildConfigExtension> {
        className("SoundBoundConfig")   // forces the class name. Defaults to 'BuildConfig'
        packageName("in.shabinder.soundbound")
        useKotlinOutput {
            internalVisibility = false
        }
        SoundBoundConfig::class.memberProperties.forEach {
            when {
                it.name.equals("INSTANCE") -> return@forEach
                it.returnType.toString() == "kotlin.Long" -> buildConfigField(it.returnType.toString(), it.name, "${it.call()}L")
                it.returnType.toString().substringAfterLast(".").equals("String") -> buildConfigField(
                    it.returnType.toString(),
                    it.name,
                    "\"${it.call()}\""
                )

                else -> buildConfigField(it.returnType.toString(), it.name, it.call().toString())
            }
        }
        configure()
    }
}

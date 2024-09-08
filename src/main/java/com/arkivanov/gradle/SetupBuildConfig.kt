package com.arkivanov.gradle

import com.github.gmazzo.buildconfig.BuildConfigExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.buildConfigField
import org.gradle.kotlin.dsl.configure
import kotlin.reflect.full.memberProperties

object SoundBoundConfig {
    const val APP_VERSION: String = "0.0.34"
    const val APP_VERSION_CODE: Long = 43L
}

enum class BuildType {
    DEBUG, RELEASE, STAGING
}

fun Project.setupBuildConfig(
    configure: BuildConfigExtension.(buildType: BuildType) -> Unit = {}
) {
    plugins.apply("com.github.gmazzo.buildconfig")
    extensions.configure<BuildConfigExtension> {
        generateAtSync.set(true)
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
        val currentBuildType = getBuildType()
        println("Current Build Type: $currentBuildType")
        buildConfigField("IS_DEBUG", currentBuildType == BuildType.DEBUG)
        configure(currentBuildType)
    }
}

// pass in the CLI, or in the gradle.properties file
// ./gradlew -P`appFlavor`=RELEASE (without ``)
// appFlavor=RELEASE (in gradle.properties)
fun Project.getBuildType(): BuildType {
    val flavor = properties["appFlavor"]?.toString() ?: BuildType.DEBUG.name
    return BuildType.valueOf(flavor.uppercase())
}

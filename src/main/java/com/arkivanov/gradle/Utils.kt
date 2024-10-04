package com.arkivanov.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import kotlin.reflect.KClass

internal val Project.multiplatformExtension: KotlinMultiplatformExtension
    get() = kotlinExtension as KotlinMultiplatformExtension

internal inline fun Project.hasExtension(clazz: () -> KClass<*>): Boolean =
    try {
        extensions.findByType(clazz()) != null
    } catch (e: NoClassDefFoundError) {
        false
    }

internal fun Project.checkIsRootProject() {
    check(rootProject == this) { "Must be called on a root project" }
}

internal val Project.deps: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("deps")

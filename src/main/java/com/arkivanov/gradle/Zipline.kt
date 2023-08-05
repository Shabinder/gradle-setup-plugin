package com.arkivanov.gradle

import app.cash.zipline.gradle.ZiplineExtension
import app.cash.zipline.loader.SignatureAlgorithmId
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ZiplineConfig(
    val port: Int,
    val version: String,
    val mainFunction: String,
    val mainModuleId: String? = null,
    val signingKeys: List<SigningKey> = emptyList(),
) {
    class SigningKey(
        val name: String,
        val privateKeyHex: String,
        val algorithmId: SignatureAlgorithmId,
    )
}

fun Project.setupZipline(config: ZiplineConfig) {
    plugins.apply("app.cash.zipline")

    extensions.configure<ZiplineExtension> {
        httpServerPort.set(config.port)
        mainFunction.set(config.mainFunction)
        config.mainModuleId?.let { mainModuleId.set(it) }

        for (key in config.signingKeys) {
            signingKeys.create(key.name) {
                privateKeyHex.set(key.privateKeyHex)
                algorithmId.set(key.algorithmId)
            }
        }
    }
}

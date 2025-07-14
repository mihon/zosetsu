/*
 * Copyright (C) 2025 AntsyLich and Mihon Open Source Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * See LICENSE and LICENSE-EXCEPTIONS.md for details.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 */
plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

kotlin {
    jvmToolchain(21)
}

// Configuration should be synced with [/gradle/build-config/src/main/kotlin/PluginSpotless.kt]
spotless {
    val ktlintVersion = libs.ktlint.cli.get().version
    kotlin {
        target("src/**/*.kt")
        ktlint(ktlintVersion).setEditorConfigPath(rootProject.file("../../.editorconfig"))
        licenseHeaderFile(rootProject.file("../../spotless/copyright.kt"))
            .updateYearWithLatest(true)
    }

    kotlinGradle {
        target("*.gradle.kts")
        ktlint(ktlintVersion).setEditorConfigPath(rootProject.file("../../.editorconfig"))
        licenseHeaderFile(rootProject.file("../../spotless/copyright.kt"), "(^(?![\\/ ]\\**).*$)")
            .updateYearWithLatest(true)
    }
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.multiplatform.gradle)
    implementation(libs.maven.publish.gradle)
    implementation(libs.spotless.gradle)
    implementation(gradleApi())

    // These allow us to reference the dependency catalog inside of our compiled plugins
    implementation(files(libs::class.java.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("kotlin-multiplatform") {
            id = zosetsu.plugins.kotlin.multiplatform.get().pluginId
            implementationClass = "PluginKotlinMultiplatform"
        }
        register("maven-publish") {
            id = zosetsu.plugins.maven.publish.get().pluginId
            implementationClass = "PluginMavenPublish"
        }
        register("spotless") {
            id = zosetsu.plugins.spotless.get().pluginId
            implementationClass = "PluginSpotless"
        }
    }
}

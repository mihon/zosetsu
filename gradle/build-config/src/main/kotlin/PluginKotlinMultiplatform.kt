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
import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import zosetsu.gradle.extensions.alias
import zosetsu.gradle.extensions.libs
import zosetsu.gradle.extensions.plugins

class PluginKotlinMultiplatform : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.android.kotlinLibrary)
            alias(libs.plugins.kotlin.multiplatform)
        }

        kotlin {
            explicitApi()
            jvmToolchain(17)

            applyDefaultHierarchyTemplate()
            @Suppress("UnstableApiUsage")
            androidLibrary {
                compileSdk = AndroidConstants.COMPILE_SDK
                minSdk = AndroidConstants.MIN_SDK
            }
            jvm {
                compilerOptions {
                    jvmDefault.set(JvmDefaultMode.NO_COMPATIBILITY)
                }
            }
        }
    }
}

private object AndroidConstants {
    const val COMPILE_SDK = 36
    const val MIN_SDK = 21
}

private fun Project.kotlin(action: KotlinMultiplatformExtension.() -> Unit) {
    extensions.configure(action)
}

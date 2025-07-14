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
import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import zosetsu.gradle.extensions.alias
import zosetsu.gradle.extensions.libs
import zosetsu.gradle.extensions.plugins

class PluginSpotless : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.spotless)
        }

        // Configuration should be synced with [/gradle/build-config/build.gradle.kts]
        val ktlintVersion = libs.ktlint.cli.get().version
        spotless {
            kotlin {
                target("src/**/*.kt")
                ktlint(ktlintVersion)
                licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
                    .updateYearWithLatest(true)
            }

            kotlinGradle {
                target("*.gradle.kts")
                ktlint(ktlintVersion)
                licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "(^(?![\\/ ]\\**).*$)")
                    .updateYearWithLatest(true)
            }
        }
    }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) {
    extensions.configure(action)
}

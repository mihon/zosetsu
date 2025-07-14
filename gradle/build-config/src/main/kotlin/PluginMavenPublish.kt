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
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import zosetsu.gradle.extensions.alias
import zosetsu.gradle.extensions.libs
import zosetsu.gradle.extensions.plugins

class PluginMavenPublish : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        plugins {
            alias(libs.plugins.maven.publish)
        }

        mavenPublishing {
            pom {
                url.set("https://github.com/mihon/zosetsu")
                inceptionYear.set("2025")

                licenses {
                    license {
                        name.set("GNU Affero General Public License v3.0 only")
                        url.set("https://www.gnu.org/licenses/agpl-3.0.en.html")
                        distribution.set("repo")
                    }
                }

                organization {
                    name.set("Mihon Open Source Project")
                    url.set("https://github.com/mihon")
                }

                scm {
                    connection.set("scm:git:git://github.com/mihon/zosetsu.git")
                    url.set("https://github.com/mihon/zosetsu")
                }
            }
        }
    }
}

private fun Project.mavenPublishing(action: MavenPublishBaseExtension.() -> Unit) {
    extensions.configure(action)
}

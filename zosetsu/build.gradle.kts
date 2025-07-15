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
@file:OptIn(ExperimentalAbiValidation::class)

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    alias(zosetsu.plugins.kotlin.multiplatform)
    alias(zosetsu.plugins.maven.publish)
    alias(zosetsu.plugins.spotless)
}

kotlin {
    abiValidation {
        enabled.set(true)
    }

    @Suppress("UnstableApiUsage")
    androidLibrary {
        namespace = "zosetsu"

        withHostTest {}
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.annotation)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

mavenPublishing {
    coordinates("com.github.zosetsu", "zosetsu", "0.1.0")

    pom {
        name.set("Zosetsu")
        description.set("Extension API for Mihon")
        inceptionYear.set("2025")

        developers {
            developer {
                id.set("antsylich")
                name.set("AntsyLich")
                url.set("https://github.com/AntsyLich")
            }
        }
    }
}

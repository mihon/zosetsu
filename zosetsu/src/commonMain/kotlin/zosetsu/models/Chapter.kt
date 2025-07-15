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
package zosetsu.models

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Suppress("CanBeParameter")
public class Chapter(
    public val key: String,
    public val language: String,
    public val volume: String? = null,
    public val number: String? = null,
    public val name: String? = null,
    public val staff: Set<Staff.Chapter> = emptySet(),
    public val type: String? = null,
    public val lockStatus: LockStatus = LockStatus.NONE,
    public val uploadedAt: Instant? = null,
    public val extras: Map<String, String> = emptyMap(),
    public val memo: Map<String, String> = emptyMap(),
) {
    init {
        require(number == null || number.matches(numberRegex))
        require(volume == null || volume.matches(numberRegex))
    }

    public enum class LockStatus(public val isLocked: Boolean) {
        NONE(isLocked = false),
        LOCKED(isLocked = true),
        UNLOCKED(isLocked = false),
    }

    internal companion object {
        val numberRegex = """^(?:\d+)+(?:\.?\d+)?[a-z]?$""".toRegex()
    }
}

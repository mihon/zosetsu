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

import androidx.annotation.FloatRange

public class Manga(
    public val key: String,
    public val title: String,
    public val coverKey: String? = null,
    public val bannerKey: String? = null,
    public val altTitles: Set<String> = emptySet(),
    public val staff: Set<Staff.Manga> = emptySet(),
    public val status: Status? = null,
    public val contentRating: ContentRating? = null,
    @param:FloatRange(from = 0.0, to = 1.0)
    public val userRating: Float? = null,
    public val description: String = "",
    public val tags: Set<Tag> = emptySet(),
    public val readingMode: ReadingMode? = null,
    public val updateStrategy: UpdateStrategy = UpdateStrategy.AlwaysUpdate,
    public val initialized: Boolean = false,
    public val extras: Map<String, String> = emptyMap(),
    public val memo: Map<String, String> = emptyMap(),
) {
    public enum class ContentRating {
        SAFE,
        SUGGESTIVE,
        ADULT,
    }

    public enum class Status {
        Ongoing,
        Completed,
        Licensed,
        PublishingFinished,
        Cancelled,
        OnHiatus,
    }

    public data class Tag(
        public val key: String,
        public val type: String?,
        public val displayName: String,
    )

    public enum class ReadingMode {
        RightToLeft,
        LeftToRight,
        LongStrip,
    }

    public enum class UpdateStrategy {
        AlwaysUpdate,
        OnlyFetchOnce,
    }
}

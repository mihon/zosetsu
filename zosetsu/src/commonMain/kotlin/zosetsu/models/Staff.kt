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

public sealed interface Staff {
    public val key: String
    public val name: String

    public sealed interface Manga : Staff
    public sealed interface Chapter : Staff

    public class Author(
        override val key: String,
        override val name: String,
    ) : Manga

    public class Artist(
        override val key: String,
        override val name: String,
    ) : Manga

    public class Circle(
        override val key: String,
        override val name: String,
    ) : Manga

    public class Translator(
        override val key: String,
        override val name: String,
    ) : Chapter

    public class Uploader(
        override val key: String,
        override val name: String,
    ) : Chapter
}

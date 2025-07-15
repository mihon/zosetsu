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

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ChapterNumberRegexTest {

    @Test
    fun `should validate chapter number with single digit`() {
        assertTrue(matches("1"))
    }

    @Test
    fun `should validate chapter number with decimal`() {
        assertTrue(matches("1.0"))
    }

    @Test
    fun `should validate chapter number with letter suffix`() {
        assertTrue(matches("1a"))
    }

    @Test
    fun `should validate chapter number with decimal and letter suffix`() {
        assertTrue(matches("1.0a"))
    }

    @Test
    fun `should invalidate chapter number with consecutive dots`() {
        assertFalse(matches("1..0"))
    }

    @Test
    fun `should invalidate chapter number with dot suffix`() {
        assertFalse(matches("1."))
    }

    private fun matches(number: String): Boolean = number.matches(Chapter.numberRegex)
}

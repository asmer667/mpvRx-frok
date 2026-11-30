/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package app.gyrolet.mpvrx.ui.browser.cards

import android.text.format.DateUtils

/** Formats persisted playback timestamps using the device locale (minutes through years). */
internal fun formatLastPlayedTime(timestampMillis: Long): String {
  val now = System.currentTimeMillis()
  return DateUtils.getRelativeTimeSpanString(
    timestampMillis,
    now,
    DateUtils.MINUTE_IN_MILLIS,
    DateUtils.FORMAT_ABBREV_RELATIVE,
  ).toString()
}

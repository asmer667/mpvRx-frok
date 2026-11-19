/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * Live preview + controls for folder and video card accent styles.
 */

package app.gyrolet.mpvrx.ui.preferences

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.gyrolet.mpvrx.preferences.AppearancePreferences
import app.gyrolet.mpvrx.preferences.preference.collectAsState
import app.gyrolet.mpvrx.presentation.components.CardAccentLivePreview
import app.gyrolet.mpvrx.presentation.components.CardAccentStyle
import org.koin.compose.koinInject

/**
 * Settings section that lets the user pick folder / video card styles
 * and control opacity with a live preview.
 *
 * Call this from AppearancePreferencesScreen (or a dedicated Card Style page).
 */
@Composable
fun CardAccentStylePreferences(
  modifier: Modifier = Modifier,
) {
  val prefs = koinInject<AppearancePreferences>()
  val folderStyleId by prefs.folderCardStyle.collectAsState()
  val videoStyleId by prefs.videoCardStyle.collectAsState()
  val opacity by prefs.cardAccentOpacity.collectAsState()

  val folderStyle = CardAccentStyle.fromId(folderStyleId)
  val videoStyle = CardAccentStyle.fromId(videoStyleId)

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    Text(
      text = "أشكال بطاقات المجلدات والفيديو",
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold,
    )

    // ── Live preview ────────────────────────────────────────────────
    Text("معاينة حية", style = MaterialTheme.typography.labelLarge)
    CardAccentLivePreview(
      style = folderStyle,
      opacity = opacity,
      isFolder = true,
      modifier = Modifier.fillMaxWidth(),
    )
    Spacer(Modifier.height(4.dp))
    CardAccentLivePreview(
      style = videoStyle,
      opacity = opacity,
      isFolder = false,
      modifier = Modifier.fillMaxWidth(),
    )

    // ── Opacity ─────────────────────────────────────────────────────
    Text(
      text = "الشفافية: ${(opacity * 100).toInt()}%",
      style = MaterialTheme.typography.labelLarge,
    )
    Slider(
      value = opacity,
      onValueChange = { prefs.cardAccentOpacity.set(it) },
      valueRange = 0.45f..1f,
      steps = 10,
      modifier = Modifier.fillMaxWidth(),
    )

    // ── Folder styles ───────────────────────────────────────────────
    Text("ستايل بطاقات المجلدات", style = MaterialTheme.typography.labelLarge)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      CardAccentStyle.folderStyles().forEach { style ->
        FilterChip(
          selected = style.id == folderStyleId,
          onClick = { prefs.folderCardStyle.set(style.id) },
          label = { Text(style.displayNameAr) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
          ),
        )
      }
    }

    // ── Video styles ────────────────────────────────────────────────
    Text("ستايل بطاقات الحلقات / الفيديو", style = MaterialTheme.typography.labelLarge)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      CardAccentStyle.videoStyles().forEach { style ->
        FilterChip(
          selected = style.id == videoStyleId,
          onClick = { prefs.videoCardStyle.set(style.id) },
          label = { Text(style.displayNameAr) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
          ),
        )
      }
    }
  }
}

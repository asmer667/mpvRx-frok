/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package app.gyrolet.mpvrx.ui.preferences

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.gyrolet.mpvrx.R
import app.gyrolet.mpvrx.preferences.AppearancePreferences
import app.gyrolet.mpvrx.preferences.preference.collectAsState
import app.gyrolet.mpvrx.presentation.components.CardAccentStyle
import app.gyrolet.mpvrx.presentation.components.cardAccentDecoration
import app.gyrolet.mpvrx.ui.utils.LocalBackStack
import app.gyrolet.mpvrx.ui.utils.popSafely
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import java.util.Locale

@Serializable
object CardAccentPreferencesScreen : ScrollableSettingsScreen {
  @Composable
  override fun Content() {
    val backstack = LocalBackStack.current
    val preferences = koinInject<AppearancePreferences>()

    val savedFolderStyle by preferences.folderCardStyle.collectAsState()
    val savedVideoStyle by preferences.videoCardStyle.collectAsState()
    val savedOpacity by preferences.cardAccentOpacity.collectAsState()

    var folderStyleId by rememberSaveable { mutableStateOf(savedFolderStyle) }
    var videoStyleId by rememberSaveable { mutableStateOf(savedVideoStyle) }
    var opacity by rememberSaveable { mutableStateOf(savedOpacity.coerceIn(0.35f, 1f)) }
    var showingFolders by rememberSaveable { mutableStateOf(true) }

    val selectedStyle = CardAccentStyle.fromId(
      if (showingFolders) folderStyleId else videoStyleId,
    )
    val styles = if (showingFolders) {
      CardAccentStyle.folderStyles()
    } else {
      CardAccentStyle.videoStyles()
    }

    Scaffold(
      topBar = {
        SettingsTopAppBar(
          title = stringResource(R.string.pref_card_customization_title),
          onBack = { backstack.popSafely() },
        )
      },
    ) { padding ->
      ProvideExpressivePreferenceLocals {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(settingsContentPadding(padding))
            .padding(horizontal = 16.dp),
        ) {
          Column(
            modifier = Modifier
              .weight(1f)
              .fillMaxWidth()
              .padding(top = 12.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
          ) {
            Text(
              stringResource(R.string.pref_card_customization_preview),
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.SemiBold,
            )

            CardAccentPreviewCard(
              style = selectedStyle,
              opacity = opacity,
              isFolder = showingFolders,
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              AccentTypeTab(
                text = stringResource(R.string.pref_card_customization_folders),
                selected = showingFolders,
                modifier = Modifier.weight(1f),
                onClick = { showingFolders = true },
              )
              AccentTypeTab(
                text = stringResource(R.string.pref_card_customization_videos),
                selected = !showingFolders,
                modifier = Modifier.weight(1f),
                onClick = { showingFolders = false },
              )
            }

            Text(
              stringResource(R.string.pref_card_customization_style),
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.SemiBold,
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
              styles.chunked(2).forEach { pair ->
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                  pair.forEach { style ->
                    AccentStyleChoice(
                      style = style,
                      selected = selectedStyle == style,
                      modifier = Modifier.weight(1f),
                      onClick = {
                        if (showingFolders) folderStyleId = style.id
                        else videoStyleId = style.id
                      },
                    )
                  }
                  if (pair.size == 1) Spacer(Modifier.weight(1f))
                }
              }
            }

            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Text(
                stringResource(R.string.pref_card_customization_opacity),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
              )
              Text(
                "${(opacity * 100).toInt()}%",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
              )
            }

            Slider(
              value = opacity,
              onValueChange = { opacity = it },
              valueRange = 0.35f..1f,
            )
          }

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 12.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
          ) {
            OutlinedButton(
              onClick = { backstack.popSafely() },
              modifier = Modifier.weight(1f),
            ) {
              Text(stringResource(R.string.pref_card_customization_cancel))
            }
            Button(
              onClick = {
                preferences.folderCardStyle.set(folderStyleId)
                preferences.videoCardStyle.set(videoStyleId)
                preferences.cardAccentOpacity.set(opacity)
                backstack.popSafely()
              },
              modifier = Modifier.weight(1f),
            ) {
              Text(stringResource(R.string.pref_card_customization_apply))
            }
          }
        }
      }
    }
  }
}

@Composable
private fun AccentTypeTab(
  text: String,
  selected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Surface(
    modifier = modifier.clip(RoundedCornerShape(14.dp)).clickable(onClick = onClick),
    color = if (selected) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.surfaceContainerLow,
    shape = RoundedCornerShape(14.dp),
  ) {
    Text(
      text = text,
      modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
      textAlign = androidx.compose.ui.text.style.TextAlign.Center,
      color = if (selected) MaterialTheme.colorScheme.onSecondaryContainer
      else MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.labelLarge,
      fontWeight = FontWeight.SemiBold,
    )
  }
}

@Composable
private fun AccentStyleChoice(
  style: CardAccentStyle,
  selected: Boolean,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  val arabic = Locale.getDefault().language == "ar"
  Surface(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick),
    shape = RoundedCornerShape(12.dp),
    color = if (selected) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.surfaceContainerLow,
    border = if (selected) androidx.compose.foundation.BorderStroke(
      1.dp,
      MaterialTheme.colorScheme.primary,
    ) else null,
  ) {
    Column(
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      Text(
        if (arabic) style.displayNameAr else style.displayNameEn,
        style = MaterialTheme.typography.labelMedium,
        color = if (selected) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.onSurface,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
      )
      if (selected) {
        Text(
          "✓",
          color = MaterialTheme.colorScheme.primary,
          style = MaterialTheme.typography.labelSmall,
        )
      }
    }
  }
}

@Composable
private fun CardAccentPreviewCard(
  style: CardAccentStyle,
  opacity: Float,
  isFolder: Boolean,
) {
  val shape = RoundedCornerShape(18.dp)
  Surface(
    modifier = Modifier.fillMaxWidth(),
    shape = shape,
    color = MaterialTheme.colorScheme.surfaceContainerLow,
    shadowElevation = 2.dp,
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(if (isFolder) 116.dp else 190.dp)
        .clip(shape),
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .cardAccentDecoration(
            style = style,
            opacity = opacity,
            shape = shape,
          ),
      )

      if (isFolder) {
        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
          Box(
            modifier = Modifier
              .size(68.dp)
              .clip(RoundedCornerShape(14.dp))
              .background(
                Brush.linearGradient(
                  listOf(Color(0xFF536DFE), Color(0xFF9B5DE5)),
                ),
              ),
            contentAlignment = Alignment.Center,
          ) {
            Text("📁", style = MaterialTheme.typography.headlineMedium)
          }
          Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
              stringResource(R.string.pref_card_customization_folder_name),
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.SemiBold,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
              stringResource(R.string.pref_card_customization_folder_info),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }
      } else {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
              .clip(RoundedCornerShape(10.dp))
              .background(
                Brush.linearGradient(
                  listOf(Color(0xFF263B65), Color(0xFF7B3F91), Color(0xFFCE7650)),
                ),
              ),
            contentAlignment = Alignment.Center,
          ) {
            Text("▶", style = MaterialTheme.typography.headlineLarge, color = Color.White)
            Text(
              "02:14:38",
              modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(7.dp)
                .background(Color.Black.copy(alpha = 0.72f), RoundedCornerShape(5.dp))
                .padding(horizontal = 6.dp, vertical = 3.dp),
              color = Color.White,
              style = MaterialTheme.typography.labelSmall,
            )
          }
          Text(
            stringResource(R.string.pref_card_customization_video_name),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Text(
            stringResource(R.string.pref_card_customization_video_info),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }
    }
  }
}

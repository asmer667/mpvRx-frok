/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * Card accent styles for folder and video cards.
 * Provides left-accent gradient strips (the preferred look) with multiple variants
 * and separate style sets for folders vs video/episode cards.
 */

package app.gyrolet.mpvrx.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Visual styles for media cards.
 * Folder cards use left-accent styles (1-10).
 * Video / episode cards use a different set (11-15).
 */
enum class CardAccentStyle(val id: Int, val displayNameAr: String, val displayNameEn: String) {
  // ── Folder styles (left accent strip) ──────────────────────────────
  FOLDER_ORANGE_PURPLE(1, "برتقالي ← بنفسجي", "Orange → Purple"),
  FOLDER_CYAN_BLUE(2, "سيان ← أزرق", "Cyan → Blue"),
  FOLDER_GOLD(3, "ذهبي", "Gold"),
  FOLDER_PINK_PURPLE(4, "وردي ← بنفسجي", "Pink → Purple"),
  FOLDER_GREEN(5, "زمردي", "Emerald"),
  FOLDER_RED_PURPLE(6, "أحمر ← بنفسجي", "Red → Purple"),
  FOLDER_BLUE(7, "أزرق عميق", "Deep Blue"),
  FOLDER_LIME(8, "ليموني", "Lime"),
  FOLDER_SUNSET(9, "غروب", "Sunset"),
  FOLDER_NEON(10, "نيون سيان", "Neon Cyan"),

  // ── Video / Episode styles (different look) ────────────────────────
  VIDEO_SOFT_BORDER(11, "حدود ناعمة", "Soft Border"),
  VIDEO_GLOW_EDGE(12, "حافة متوهجة", "Glow Edge"),
  VIDEO_MINIMAL(13, "بسيط", "Minimal"),
  VIDEO_ACCENT_TOP(14, "شريط علوي", "Top Accent"),
  VIDEO_DOUBLE_EDGE(15, "حافة مزدوجة", "Double Edge");

  companion object {
    fun folderStyles(): List<CardAccentStyle> = entries.filter { it.id in 1..10 }
    fun videoStyles(): List<CardAccentStyle> = entries.filter { it.id in 11..15 }
    fun fromId(id: Int): CardAccentStyle = entries.find { it.id == id } ?: FOLDER_ORANGE_PURPLE
  }
}

private fun styleColors(style: CardAccentStyle): Pair<Color, Color> = when (style) {
  CardAccentStyle.FOLDER_ORANGE_PURPLE -> Color(0xFFFF6B35) to Color(0xFF9B5DE5)
  CardAccentStyle.FOLDER_CYAN_BLUE     -> Color(0xFF00F5D4) to Color(0xFF00BBF9)
  CardAccentStyle.FOLDER_GOLD          -> Color(0xFFFFD166) to Color(0xFFFF9F1C)
  CardAccentStyle.FOLDER_PINK_PURPLE   -> Color(0xFFFF85A1) to Color(0xFF9B5DE5)
  CardAccentStyle.FOLDER_GREEN         -> Color(0xFF2EC4B6) to Color(0xFF00A896)
  CardAccentStyle.FOLDER_RED_PURPLE    -> Color(0xFFE63946) to Color(0xFF9B5DE5)
  CardAccentStyle.FOLDER_BLUE          -> Color(0xFF00B4D8) to Color(0xFF0077B6)
  CardAccentStyle.FOLDER_LIME          -> Color(0xFF80ED99) to Color(0xFF38A3A5)
  CardAccentStyle.FOLDER_SUNSET        -> Color(0xFFFF9F1C) to Color(0xFFFF006E)
  CardAccentStyle.FOLDER_NEON          -> Color(0xFF00F5D4) to Color(0xFF48CAE4)

  CardAccentStyle.VIDEO_SOFT_BORDER    -> Color(0xFF9B5DE5) to Color(0xFF00BBF9)
  CardAccentStyle.VIDEO_GLOW_EDGE      -> Color(0xFFFF6B35) to Color(0xFFE63946)
  CardAccentStyle.VIDEO_MINIMAL        -> Color(0xFF4CC9F0) to Color(0xFF4361EE)
  CardAccentStyle.VIDEO_ACCENT_TOP     -> Color(0xFFFFD166) to Color(0xFFFF9F1C)
  CardAccentStyle.VIDEO_DOUBLE_EDGE    -> Color(0xFFC77DFF) to Color(0xFFF15BB5)
}

/**
 * Applies the preferred left-accent style (or video variant) to a card.
 *
 * - Folder styles draw a thick vertical gradient strip on the left only.
 * - Video styles use softer full borders or top accents so they look different.
 * - The main card body stays dark so text and icons remain readable.
 */
fun Modifier.cardAccentStyle(
  style: CardAccentStyle,
  opacity: Float = 1f,
  accentWidth: Dp = 7.dp,
  cornerRadius: Dp = 16.dp,
): Modifier {
  val (startColor, endColor) = styleColors(style)
  val shape = RoundedCornerShape(cornerRadius)
  val safeOpacity = opacity.coerceIn(0.35f, 1f)
  val darkBase = Color(0xFF16162A).copy(alpha = safeOpacity)

  return when (style) {
    // ── Folder left-accent styles ────────────────────────────────────
    in CardAccentStyle.folderStyles() -> {
      this
        .clip(shape)
        .drawBehind {
          val widthPx = accentWidth.toPx()
          val radius = cornerRadius.toPx()
          // Draw the left accent slightly wider so it sits under the rounded corner
          drawRoundRect(
            brush = Brush.verticalGradient(
              colors = listOf(
                startColor.copy(alpha = safeOpacity),
                endColor.copy(alpha = safeOpacity),
              ),
            ),
            topLeft = Offset(0f, 0f),
            size = Size(widthPx + radius * 0.6f, size.height),
            cornerRadius = CornerRadius(radius, radius),
          )
        }
        .background(color = darkBase, shape = shape)
    }

    // ── Video styles ─────────────────────────────────────────────────
    CardAccentStyle.VIDEO_SOFT_BORDER -> {
      this
        .clip(shape)
        .background(color = darkBase, shape = shape)
        .border(
          width = 1.8.dp,
          brush = Brush.linearGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
          shape = shape,
        )
    }

    CardAccentStyle.VIDEO_GLOW_EDGE -> {
      this
        .clip(shape)
        .background(color = darkBase, shape = shape)
        .border(
          width = 2.2.dp,
          brush = Brush.linearGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
          shape = shape,
        )
    }

    CardAccentStyle.VIDEO_MINIMAL -> {
      this
        .clip(shape)
        .background(color = darkBase, shape = shape)
        .border(
          width = 1.4.dp,
          brush = SolidColor(startColor.copy(alpha = safeOpacity * 0.7f)),
          shape = shape,
        )
    }

    CardAccentStyle.VIDEO_ACCENT_TOP -> {
      this
        .clip(shape)
        .drawBehind {
          val heightPx = 5.dp.toPx()
          val radius = cornerRadius.toPx()
          drawRoundRect(
            brush = Brush.horizontalGradient(
              colors = listOf(
                startColor.copy(alpha = safeOpacity),
                endColor.copy(alpha = safeOpacity),
              ),
            ),
            topLeft = Offset(0f, 0f),
            size = Size(size.width, heightPx + radius * 0.4f),
            cornerRadius = CornerRadius(radius, radius),
          )
        }
        .background(color = darkBase, shape = shape)
    }

    CardAccentStyle.VIDEO_DOUBLE_EDGE -> {
      this
        .clip(shape)
        .background(color = darkBase, shape = shape)
        .border(
          width = 2.5.dp,
          brush = Brush.linearGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
          shape = shape,
        )
    }

    else -> {
      this
        .clip(shape)
        .background(color = darkBase, shape = shape)
    }
  }
}

/**
 * Draws card accent decoration without painting an opaque card background.
 * Intended for overlay layers above selection backgrounds and below card content.
 */
fun Modifier.cardAccentDecoration(
  style: CardAccentStyle,
  opacity: Float = 1f,
  accentWidth: Dp = 7.dp,
  shape: Shape = RoundedCornerShape(16.dp),
): Modifier {
  val (startColor, endColor) = styleColors(style)
  val safeOpacity = opacity.coerceIn(0.35f, 1f)

  return when (style) {
    in CardAccentStyle.folderStyles() -> {
      this
        .clip(shape)
        .drawBehind {
          val widthPx = accentWidth.toPx()
          drawRect(
            brush = Brush.verticalGradient(
              colors = listOf(
                startColor.copy(alpha = safeOpacity),
                endColor.copy(alpha = safeOpacity),
              ),
            ),
            topLeft = Offset.Zero,
            size = Size(widthPx, size.height),
          )
        }
    }

    CardAccentStyle.VIDEO_SOFT_BORDER,
    CardAccentStyle.VIDEO_GLOW_EDGE,
    CardAccentStyle.VIDEO_MINIMAL,
    CardAccentStyle.VIDEO_DOUBLE_EDGE -> {
      val width = when (style) {
        CardAccentStyle.VIDEO_SOFT_BORDER -> 1.8.dp
        CardAccentStyle.VIDEO_GLOW_EDGE -> 2.2.dp
        CardAccentStyle.VIDEO_MINIMAL -> 1.4.dp
        else -> 2.5.dp
      }
      val brush =
        if (style == CardAccentStyle.VIDEO_MINIMAL) {
          SolidColor(startColor.copy(alpha = safeOpacity * 0.7f))
        } else {
          Brush.linearGradient(
            listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
          )
        }
      this.clip(shape).border(width = width, brush = brush, shape = shape)
    }

    CardAccentStyle.VIDEO_ACCENT_TOP -> {
      this
        .clip(shape)
        .drawBehind {
          drawRect(
            brush = Brush.horizontalGradient(
              colors = listOf(
                startColor.copy(alpha = safeOpacity),
                endColor.copy(alpha = safeOpacity),
              ),
            ),
            topLeft = Offset.Zero,
            size = Size(size.width, 5.dp.toPx()),
          )
        }
      }
      else -> this
    }
  }

/**
 * Live preview card used in Settings.
 * Shows a realistic mini folder / video card so the user can judge the style.
 */
@Composable
fun CardAccentLivePreview(
  style: CardAccentStyle,
  opacity: Float,
  isFolder: Boolean,
  modifier: Modifier = Modifier,
) {
  val shape = RoundedCornerShape(14.dp)
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(72.dp)
      .cardAccentStyle(style = style, opacity = opacity, accentWidth = 6.dp, cornerRadius = 14.dp)
      .padding(horizontal = 14.dp, vertical = 10.dp),
  ) {
    androidx.compose.foundation.layout.Row(
      modifier = Modifier.fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      // Fake icon
      Box(
        modifier = Modifier
          .width(36.dp)
          .height(36.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(
            Brush.linearGradient(
              listOf(
                styleColors(style).first.copy(alpha = 0.9f),
                styleColors(style).second.copy(alpha = 0.9f),
              ),
            ),
          ),
        contentAlignment = Alignment.Center,
      ) {
        Text(
          text = if (isFolder) "📁" else "▶",
          fontSize = 16.sp,
        )
      }

      androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(12.dp))

      androidx.compose.foundation.layout.Column(modifier = Modifier.weight(1f)) {
        Text(
          text = if (isFolder) "مجلد تجريبي" else "حلقة تجريبية",
          color = Color.White.copy(alpha = 0.95f),
          fontWeight = FontWeight.SemiBold,
          fontSize = 14.sp,
          maxLines = 1,
        )
        Text(
          text = if (isFolder) "13 مقطع · 29س" else "2:14:38 · 720p",
          color = Color.White.copy(alpha = 0.55f),
          fontSize = 12.sp,
          maxLines = 1,
        )
      }
    }
  }
}

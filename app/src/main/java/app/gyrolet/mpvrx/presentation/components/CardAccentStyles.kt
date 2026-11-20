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
  FOLDER_AMETHYST(21, "أماثيست", "Amethyst"),
  FOLDER_TURQUOISE(22, "فيروزي", "Turquoise"),
  FOLDER_ROSE(23, "وردي ناعم", "Rose"),
  FOLDER_SAPPHIRE(24, "ياقوت أزرق", "Sapphire"),
  FOLDER_COPPER(25, "نحاسي", "Copper"),
  FOLDER_ARCTIC(26, "قطبي", "Arctic"),
  FOLDER_LAVENDER(27, "لافندر", "Lavender"),
  FOLDER_MINT(28, "نعناعي", "Mint"),
  FOLDER_RUBY(29, "ياقوتي", "Ruby"),
  FOLDER_AMBER(30, "كهرماني", "Amber"),
  FOLDER_MIDNIGHT(31, "منتصف الليل", "Midnight"),
  FOLDER_LAGOON(32, "بحيرة", "Lagoon"),
  FOLDER_CHERRY(33, "كرزي", "Cherry"),
  FOLDER_ICE(34, "جليدي", "Ice"),
  FOLDER_FOREST(35, "غابة", "Forest"),
  FOLDER_PLASMA(36, "بلازما", "Plasma"),
  FOLDER_SAND(37, "رملي", "Sand"),
  FOLDER_STEEL(38, "فولاذي", "Steel"),
  FOLDER_DRAGON(39, "تنيني", "Dragon"),
  FOLDER_COSMIC(40, "كوني", "Cosmic"),

  // ── Video / Episode styles (different look) ────────────────────────
  VIDEO_SOFT_BORDER(11, "حدود ناعمة", "Soft Border"),
  VIDEO_GLOW_EDGE(12, "حافة متوهجة", "Glow Edge"),
  VIDEO_MINIMAL(13, "بسيط", "Minimal"),
  VIDEO_ACCENT_TOP(14, "شريط علوي", "Top Accent"),
  VIDEO_DOUBLE_EDGE(15, "حافة مزدوجة", "Double Edge"),
  VIDEO_GLASS_PANEL(16, "لوحة زجاجية", "Glass Panel"),
  VIDEO_NEON_FRAME(17, "إطار نيون", "Neon Frame"),
  VIDEO_SIDE_ACCENT(18, "شريط جانبي", "Side Accent"),
  VIDEO_DIAGONAL(19, "تدرج قطري", "Diagonal"),
  VIDEO_LAYERED(20, "طبقات بارزة", "Layered"),
  VIDEO_AURORA_FRAME(41, "إطار الشفق", "Aurora Frame"),
  VIDEO_CINEMA_BAR(42, "شريط سينمائي", "Cinema Bar"),
  VIDEO_GLASS_GLOW(43, "زجاج متوهج", "Glass Glow"),
  VIDEO_CORNER_LIGHT(44, "إضاءة الزوايا", "Corner Light"),
  VIDEO_DOUBLE_RAIL(45, "سكتان مزدوجتان", "Double Rail"),
  VIDEO_HOLOGRAM(46, "هولوغرام", "Hologram"),
  VIDEO_MATTE(47, "مطفي", "Matte"),
  VIDEO_GOLD_LINE(48, "خط ذهبي", "Gold Line"),
  VIDEO_COLOR_WASH(49, "غسيل لوني", "Color Wash"),
  VIDEO_CUTOUT(50, "إطار مقطوع", "Cutout"),
  VIDEO_ORBIT(51, "مداري", "Orbit"),
  VIDEO_PRISM(52, "منشوري", "Prism"),
  VIDEO_FILMSTRIP(53, "شريط فيلم", "Filmstrip"),
  VIDEO_SHADOW_EDGE(54, "حافة ظل", "Shadow Edge"),
  VIDEO_SPLIT_TONE(55, "لون منقسم", "Split Tone"),
  VIDEO_WAVE(56, "موجة", "Wave"),
  VIDEO_FRAME_INSET(57, "إطار داخلي", "Inset Frame"),
  VIDEO_LASER(58, "ليزر", "Laser"),
  VIDEO_CHROME(59, "كروم", "Chrome"),
  VIDEO_OPAL(60, "أوبال", "Opal"),

  // ── Episode and Movie styles (61-90) ─────────────────────────────
  EPISODE_CINEMA(61, "سينمائي", "Cinema"),
  EPISODE_GLASS(62, "زجاجي", "Glass"),
  EPISODE_POSTER(63, "ملصق سينمائي", "Movie Poster"),
  EPISODE_NEON_FRAME(64, "إطار نيون", "Neon Frame"),
  EPISODE_GOLDEN(65, "ذهبي فاخر", "Golden Luxury"),
  EPISODE_SIDE_RAIL(66, "شريط جانبي", "Side Rail"),
  EPISODE_SPLIT(67, "منقسم", "Split Frame"),
  EPISODE_GRADIENT(68, "تدرج ملون", "Gradient"),
  EPISODE_CORNER_MARKS(69, "زوايا بارزة", "Corner Marks"),
  EPISODE_DOUBLE_LAYER(70, "طبقتان بارزتان", "Double Layer"),
  EPISODE_SPOTLIGHT(71, "إضاءة مسرحية", "Spotlight"),
  EPISODE_MINIMAL_DARK(72, "داكن أنيق", "Minimal Dark"),
  EPISODE_COLOR_RIBBON(73, "شريط ملون", "Color Ribbon"),
  EPISODE_GLASS_EDGE(74, "حافة زجاجية", "Glass Edge"),
  EPISODE_POSTER_STACK(75, "ملصقات متراكبة", "Poster Stack"),
  EPISODE_SPECTRUM(76, "طيف الألوان", "Spectrum"),
  EPISODE_FRAME_TOP_BOTTOM(77, "إطار علوي وسفلي", "Top Bottom Frame"),
  EPISODE_DIAGONAL(78, "قطري", "Diagonal"),
  EPISODE_PULSE(79, "نبض مضيء", "Pulse Glow"),
  EPISODE_METALLIC(80, "معدني", "Metallic"),
  EPISODE_VIOLET_HAZE(81, "وهج بنفسجي", "Violet Haze"),
  EPISODE_OCEAN(82, "محيطي", "Ocean"),
  EPISODE_CRIMSON(83, "قرمزي", "Crimson"),
  EPISODE_EMERALD(84, "زمردي", "Emerald"),
  EPISODE_RETRO(85, "كلاسيكي", "Retro"),
  EPISODE_FROST(86, "جليدي", "Frost"),
  EPISODE_CARBON(87, "كربوني", "Carbon"),
  EPISODE_AURORA(88, "الشفق القطبي", "Aurora"),
  EPISODE_PREMIUM(89, "بريميوم", "Premium"),
  EPISODE_ULTRA(90, "ألترا", "Ultra");

  companion object {
    fun folderStyles(): List<CardAccentStyle> = entries.filter { it.id in 1..10 || it.id in 21..40 }
    fun videoStyles(): List<CardAccentStyle> = entries.filter { it.id in 11..20 || it.id in 41..60 }
    fun episodeStyles(): List<CardAccentStyle> = entries.filter { it.id in 61..90 }
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
  CardAccentStyle.FOLDER_AMETHYST -> Color(0xFFB388FF) to Color(0xFF6200EA)
  CardAccentStyle.FOLDER_TURQUOISE -> Color(0xFF40E0D0) to Color(0xFF008B8B)
  CardAccentStyle.FOLDER_ROSE -> Color(0xFFFF80AB) to Color(0xFFC2185B)
  CardAccentStyle.FOLDER_SAPPHIRE -> Color(0xFF448AFF) to Color(0xFF1A237E)
  CardAccentStyle.FOLDER_COPPER -> Color(0xFFFFAB91) to Color(0xFFBF360C)
  CardAccentStyle.FOLDER_ARCTIC -> Color(0xFFB3E5FC) to Color(0xFF0288D1)
  CardAccentStyle.FOLDER_LAVENDER -> Color(0xFFE1BEE7) to Color(0xFF7B1FA2)
  CardAccentStyle.FOLDER_MINT -> Color(0xFFA5D6A7) to Color(0xFF00897B)
  CardAccentStyle.FOLDER_RUBY -> Color(0xFFFF5252) to Color(0xFF880E4F)
  CardAccentStyle.FOLDER_AMBER -> Color(0xFFFFD54F) to Color(0xFFFF6F00)
  CardAccentStyle.FOLDER_MIDNIGHT -> Color(0xFF3949AB) to Color(0xFF121858)
  CardAccentStyle.FOLDER_LAGOON -> Color(0xFF26C6DA) to Color(0xFF006064)
  CardAccentStyle.FOLDER_CHERRY -> Color(0xFFFF1744) to Color(0xFFAD1457)
  CardAccentStyle.FOLDER_ICE -> Color(0xFFE1F5FE) to Color(0xFF4FC3F7)
  CardAccentStyle.FOLDER_FOREST -> Color(0xFF66BB6A) to Color(0xFF1B5E20)
  CardAccentStyle.FOLDER_PLASMA -> Color(0xFFE040FB) to Color(0xFF651FFF)
  CardAccentStyle.FOLDER_SAND -> Color(0xFFFFCC80) to Color(0xFF8D6E63)
  CardAccentStyle.FOLDER_STEEL -> Color(0xFFB0BEC5) to Color(0xFF455A64)
  CardAccentStyle.FOLDER_DRAGON -> Color(0xFFFF7043) to Color(0xFF4A148C)
  CardAccentStyle.FOLDER_COSMIC -> Color(0xFF7C4DFF) to Color(0xFF00E5FF)

  CardAccentStyle.VIDEO_SOFT_BORDER    -> Color(0xFF9B5DE5) to Color(0xFF00BBF9)
  CardAccentStyle.VIDEO_GLOW_EDGE      -> Color(0xFFFF6B35) to Color(0xFFE63946)
  CardAccentStyle.VIDEO_MINIMAL        -> Color(0xFF4CC9F0) to Color(0xFF4361EE)
  CardAccentStyle.VIDEO_ACCENT_TOP     -> Color(0xFFFFD166) to Color(0xFFFF9F1C)
  CardAccentStyle.VIDEO_DOUBLE_EDGE    -> Color(0xFFC77DFF) to Color(0xFFF15BB5)
  CardAccentStyle.VIDEO_GLASS_PANEL    -> Color(0xFF80DEEA) to Color(0xFFB39DDB)
  CardAccentStyle.VIDEO_NEON_FRAME     -> Color(0xFF00F5D4) to Color(0xFFFF00E5)
  CardAccentStyle.VIDEO_SIDE_ACCENT    -> Color(0xFFFF9F1C) to Color(0xFFFF006E)
  CardAccentStyle.VIDEO_DIAGONAL       -> Color(0xFF4361EE) to Color(0xFFF72585)
  CardAccentStyle.VIDEO_LAYERED        -> Color(0xFF7B2CBF) to Color(0xFF00B4D8)
  CardAccentStyle.VIDEO_AURORA_FRAME -> Color(0xFF00E5A8) to Color(0xFF7C4DFF)
  CardAccentStyle.VIDEO_CINEMA_BAR -> Color(0xFFFFD166) to Color(0xFFEF476F)
  CardAccentStyle.VIDEO_GLASS_GLOW -> Color(0xFF80DEEA) to Color(0xFFB388FF)
  CardAccentStyle.VIDEO_CORNER_LIGHT -> Color(0xFFFFAB91) to Color(0xFFFF5252)
  CardAccentStyle.VIDEO_DOUBLE_RAIL -> Color(0xFF40C4FF) to Color(0xFF304FFE)
  CardAccentStyle.VIDEO_HOLOGRAM -> Color(0xFF18FFFF) to Color(0xFFE040FB)
  CardAccentStyle.VIDEO_MATTE -> Color(0xFF90A4AE) to Color(0xFF546E7A)
  CardAccentStyle.VIDEO_GOLD_LINE -> Color(0xFFFFD700) to Color(0xFFFF8F00)
  CardAccentStyle.VIDEO_COLOR_WASH -> Color(0xFFFF80AB) to Color(0xFF7C4DFF)
  CardAccentStyle.VIDEO_CUTOUT -> Color(0xFF69F0AE) to Color(0xFF00BFA5)
  CardAccentStyle.VIDEO_ORBIT -> Color(0xFF448AFF) to Color(0xFFEA80FC)
  CardAccentStyle.VIDEO_PRISM -> Color(0xFF00E5FF) to Color(0xFFFF4081)
  CardAccentStyle.VIDEO_FILMSTRIP -> Color(0xFFFFB74D) to Color(0xFF6D4C41)
  CardAccentStyle.VIDEO_SHADOW_EDGE -> Color(0xFF78909C) to Color(0xFF263238)
  CardAccentStyle.VIDEO_SPLIT_TONE -> Color(0xFFFF5252) to Color(0xFF448AFF)
  CardAccentStyle.VIDEO_WAVE -> Color(0xFF00BFA5) to Color(0xFF2979FF)
  CardAccentStyle.VIDEO_FRAME_INSET -> Color(0xFFB388FF) to Color(0xFF536DFE)
  CardAccentStyle.VIDEO_LASER -> Color(0xFFFF1744) to Color(0xFF00E5FF)
  CardAccentStyle.VIDEO_CHROME -> Color(0xFFECEFF1) to Color(0xFF607D8B)
  CardAccentStyle.VIDEO_OPAL -> Color(0xFF80CBC4) to Color(0xFFCE93D8)

  CardAccentStyle.EPISODE_CINEMA -> Color(0xFFEF476F) to Color(0xFFFFD166)
  CardAccentStyle.EPISODE_GLASS -> Color(0xFF80DEEA) to Color(0xFFB39DDB)
  CardAccentStyle.EPISODE_POSTER -> Color(0xFFFF8A65) to Color(0xFFCE93D8)
  CardAccentStyle.EPISODE_NEON_FRAME -> Color(0xFF00F5D4) to Color(0xFFFF00E5)
  CardAccentStyle.EPISODE_GOLDEN -> Color(0xFFFFD700) to Color(0xFFFFA000)
  CardAccentStyle.EPISODE_SIDE_RAIL -> Color(0xFF00B4D8) to Color(0xFF0077B6)
  CardAccentStyle.EPISODE_SPLIT -> Color(0xFFFF6B6B) to Color(0xFF6C63FF)
  CardAccentStyle.EPISODE_GRADIENT -> Color(0xFF00C9A7) to Color(0xFF845EC2)
  CardAccentStyle.EPISODE_CORNER_MARKS -> Color(0xFFFF9F1C) to Color(0xFFFF006E)
  CardAccentStyle.EPISODE_DOUBLE_LAYER -> Color(0xFF7B2CBF) to Color(0xFF00B4D8)
  CardAccentStyle.EPISODE_SPOTLIGHT -> Color(0xFFFFE082) to Color(0xFFFF7043)
  CardAccentStyle.EPISODE_MINIMAL_DARK -> Color(0xFF90A4AE) to Color(0xFF455A64)
  CardAccentStyle.EPISODE_COLOR_RIBBON -> Color(0xFFFF4D6D) to Color(0xFF4CC9F0)
  CardAccentStyle.EPISODE_GLASS_EDGE -> Color(0xFFB2EBF2) to Color(0xFF80CBC4)
  CardAccentStyle.EPISODE_POSTER_STACK -> Color(0xFF9C27B0) to Color(0xFFFF9800)
  CardAccentStyle.EPISODE_SPECTRUM -> Color(0xFFF72585) to Color(0xFF4CC9F0)
  CardAccentStyle.EPISODE_FRAME_TOP_BOTTOM -> Color(0xFFFFD166) to Color(0xFF06D6A0)
  CardAccentStyle.EPISODE_DIAGONAL -> Color(0xFF4361EE) to Color(0xFFF72585)
  CardAccentStyle.EPISODE_PULSE -> Color(0xFF00F5D4) to Color(0xFF7B2FF7)
  CardAccentStyle.EPISODE_METALLIC -> Color(0xFFCFD8DC) to Color(0xFF78909C)
  CardAccentStyle.EPISODE_VIOLET_HAZE -> Color(0xFFB388FF) to Color(0xFF7C4DFF)
  CardAccentStyle.EPISODE_OCEAN -> Color(0xFF00B4D8) to Color(0xFF03045E)
  CardAccentStyle.EPISODE_CRIMSON -> Color(0xFFFF1744) to Color(0xFF880E4F)
  CardAccentStyle.EPISODE_EMERALD -> Color(0xFF69F0AE) to Color(0xFF00897B)
  CardAccentStyle.EPISODE_RETRO -> Color(0xFFFFB74D) to Color(0xFF8D6E63)
  CardAccentStyle.EPISODE_FROST -> Color(0xFFB3E5FC) to Color(0xFF4FC3F7)
  CardAccentStyle.EPISODE_CARBON -> Color(0xFF90A4AE) to Color(0xFF263238)
  CardAccentStyle.EPISODE_AURORA -> Color(0xFF00E5A8) to Color(0xFF7C4DFF)
  CardAccentStyle.EPISODE_PREMIUM -> Color(0xFFFFD700) to Color(0xFFB76E79)
  CardAccentStyle.EPISODE_ULTRA -> Color(0xFF00E5FF) to Color(0xFFFF00C8)
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
  val safeOpacity = opacity.coerceIn(0f, 1f)
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
  val safeOpacity = opacity.coerceIn(0f, 1f)

  return when (style) {
    in CardAccentStyle.folderStyles() -> {
      this.clip(shape).drawBehind {
        val accent = Brush.linearGradient(
          listOf(
            startColor.copy(alpha = safeOpacity),
            endColor.copy(alpha = safeOpacity),
          ),
        )
        val inset = 4.dp.toPx()
        val radius = 15.dp.toPx()

        when (style.id) {
          1 -> {
            drawRoundRect(
              brush = accent,
              cornerRadius = CornerRadius(radius, radius),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.5.dp.toPx()),
            )
            drawRect(
              brush = Brush.verticalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
              size = Size(5.dp.toPx(), size.height),
            )
          }
          2 -> {
            drawRoundRect(
              brush = accent,
              cornerRadius = CornerRadius(radius, radius),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
            )
            drawCircle(
              brush = accent,
              radius = 7.dp.toPx(),
              center = Offset(size.width - 17.dp.toPx(), 17.dp.toPx()),
            )
          }
          3 -> {
            drawRoundRect(
              color = startColor.copy(alpha = safeOpacity * 0.16f),
              cornerRadius = CornerRadius(radius, radius),
            )
            drawRoundRect(
              brush = accent,
              topLeft = Offset(inset, inset),
              size = Size(size.width - inset * 2, size.height - inset * 2),
              cornerRadius = CornerRadius(11.dp.toPx(), 11.dp.toPx()),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
            )
            drawLine(
              endColor.copy(alpha = safeOpacity),
              Offset(12.dp.toPx(), size.height - 9.dp.toPx()),
              Offset(size.width - 12.dp.toPx(), size.height - 9.dp.toPx()),
              strokeWidth = 2.dp.toPx(),
            )
          }
          4 -> {
            val mark = 15.dp.toPx()
            val stroke = 3.dp.toPx()
            drawLine(startColor.copy(alpha = safeOpacity), Offset(inset, inset), Offset(inset + mark, inset), strokeWidth = stroke)
            drawLine(startColor.copy(alpha = safeOpacity), Offset(inset, inset), Offset(inset, inset + mark), strokeWidth = stroke)
            drawLine(endColor.copy(alpha = safeOpacity), Offset(size.width - inset, size.height - inset), Offset(size.width - inset - mark, size.height - inset), strokeWidth = stroke)
            drawLine(endColor.copy(alpha = safeOpacity), Offset(size.width - inset, size.height - inset), Offset(size.width - inset, size.height - inset - mark), strokeWidth = stroke)
          }
          5 -> {
            drawRoundRect(
              color = startColor.copy(alpha = safeOpacity * 0.14f),
              cornerRadius = CornerRadius(radius, radius),
            )
            drawRoundRect(
              brush = accent,
              topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
              size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
              cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
            )
            drawRect(
              brush = Brush.verticalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
              size = Size(5.dp.toPx(), size.height),
            )
          }

          6 -> {
            drawRoundRect(
              color = startColor.copy(alpha = safeOpacity * 0.12f),
              cornerRadius = CornerRadius(radius, radius),
            )
            drawRoundRect(
              brush = accent,
              cornerRadius = CornerRadius(radius, radius),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx()),
            )
            drawRoundRect(
              color = endColor.copy(alpha = safeOpacity * 0.8f),
              topLeft = Offset(7.dp.toPx(), 7.dp.toPx()),
              size = Size(size.width - 14.dp.toPx(), size.height - 14.dp.toPx()),
              cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
            )
          }
          7 -> {
            drawRoundRect(
              color = endColor.copy(alpha = safeOpacity * 0.18f),
              cornerRadius = CornerRadius(radius, radius),
            )
            drawCircle(
              brush = accent,
              radius = size.minDimension * 0.38f,
              center = Offset(size.width * 0.82f, size.height * 0.18f),
            )
            drawRoundRect(
              color = Color.White.copy(alpha = safeOpacity * 0.45f),
              topLeft = Offset(9.dp.toPx(), 8.dp.toPx()),
              size = Size(size.width * 0.42f, 1.5.dp.toPx()),
              cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
            )
          }
          8 -> {
            drawRoundRect(
              brush = accent,
              topLeft = Offset(0f, size.height - 7.dp.toPx()),
              size = Size(size.width, 7.dp.toPx()),
              cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
            )
            drawRoundRect(
              color = startColor.copy(alpha = safeOpacity * 0.18f),
              cornerRadius = CornerRadius(radius, radius),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
            )
            drawRect(
              brush = accent,
              size = Size(5.dp.toPx(), size.height),
            )
          }
          9 -> {
            drawRoundRect(
              brush = Brush.linearGradient(
                listOf(
                  startColor.copy(alpha = safeOpacity * 0.38f),
                  endColor.copy(alpha = safeOpacity * 0.12f),
                ),
              ),
              cornerRadius = CornerRadius(radius, radius),
            )
            drawRoundRect(
              brush = accent,
              topLeft = Offset(inset, inset),
              size = Size(size.width - inset * 2, size.height - inset * 2),
              cornerRadius = CornerRadius(11.dp.toPx(), 11.dp.toPx()),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
            )
            drawLine(
              Color.White.copy(alpha = safeOpacity * 0.5f),
              Offset(12.dp.toPx(), size.height - 12.dp.toPx()),
              Offset(size.width - 12.dp.toPx(), 12.dp.toPx()),
              strokeWidth = 1.5.dp.toPx(),
            )
          }
          10 -> {
            drawRoundRect(
              brush = accent,
              cornerRadius = CornerRadius(radius, radius),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx()),
            )
            drawRoundRect(
              color = Color.Black.copy(alpha = safeOpacity * 0.16f),
              topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
              size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
              cornerRadius = CornerRadius(11.dp.toPx(), 11.dp.toPx()),
            )
            drawRoundRect(
              brush = accent,
              topLeft = Offset(9.dp.toPx(), 9.dp.toPx()),
              size = Size(size.width - 18.dp.toPx(), size.height - 18.dp.toPx()),
              cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
              style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
            )
            drawCircle(
              color = endColor.copy(alpha = safeOpacity),
              radius = 3.dp.toPx(),
              center = Offset(size.width - 15.dp.toPx(), 15.dp.toPx()),
            )
          }
          21 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(18.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 5.dp.toPx(), Offset(size.width - 17.dp.toPx(), 17.dp.toPx()))
          }
          22 -> {
            drawRect(startColor.copy(alpha = safeOpacity), size = Size(size.width, 5.dp.toPx()))
            drawRect(endColor.copy(alpha = safeOpacity), topLeft = Offset(0f, size.height - 5.dp.toPx()), size = Size(size.width, 5.dp.toPx()))
            drawLine(Color.White.copy(alpha = safeOpacity * 0.45f), Offset(10.dp.toPx(), 9.dp.toPx()), Offset(size.width - 10.dp.toPx(), 9.dp.toPx()), 1.dp.toPx())
          }
          23 -> {
            drawLine(startColor.copy(alpha = safeOpacity), Offset(0f, size.height * 0.25f), Offset(size.width * 0.72f, size.height), 4.dp.toPx())
            drawLine(endColor.copy(alpha = safeOpacity), Offset(size.width * 0.38f, 0f), Offset(size.width, size.height * 0.72f), 4.dp.toPx())
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.25f), cornerRadius = CornerRadius(14.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          24 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(17.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(4.dp.toPx()))
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.25f), topLeft = Offset(7.dp.toPx(), 7.dp.toPx()), size = Size(size.width - 14.dp.toPx(), size.height - 14.dp.toPx()), cornerRadius = CornerRadius(11.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          25 -> {
            drawCircle(startColor.copy(alpha = safeOpacity * 0.25f), size.minDimension * 0.42f, Offset(size.width, 0f))
            drawCircle(endColor.copy(alpha = safeOpacity), 5.dp.toPx(), Offset(size.width - 14.dp.toPx(), 14.dp.toPx()))
            drawLine(startColor.copy(alpha = safeOpacity), Offset(0f, size.height - 4.dp.toPx()), Offset(size.width, size.height - 4.dp.toPx()), 4.dp.toPx())
          }
          26 -> {
            drawRect(brush = accent, size = Size(8.dp.toPx(), size.height))
            drawRect(endColor.copy(alpha = safeOpacity * 0.55f), topLeft = Offset(8.dp.toPx(), 0f), size = Size(size.width - 8.dp.toPx(), 4.dp.toPx()))
            drawCircle(startColor.copy(alpha = safeOpacity), 4.dp.toPx(), Offset(size.width - 15.dp.toPx(), size.height - 15.dp.toPx()))
          }
          27 -> {
            drawCircle(startColor.copy(alpha = safeOpacity * 0.18f), size.minDimension * 0.38f, Offset(size.width, 0f))
            drawCircle(endColor.copy(alpha = safeOpacity * 0.75f), 8.dp.toPx(), Offset(size.width - 18.dp.toPx(), 18.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(15.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
          }
          28 -> {
            for (i in 0..4) drawCircle(startColor.copy(alpha = safeOpacity), 2.5.dp.toPx(), Offset(13.dp.toPx() + i * 9.dp.toPx(), size.height - 12.dp.toPx()))
            drawRoundRect(endColor.copy(alpha = safeOpacity), size = Size(5.dp.toPx(), size.height), cornerRadius = CornerRadius(3.dp.toPx()))
          }
          29 -> {
            for (i in 0..3) drawLine(if (i % 2 == 0) startColor.copy(alpha = safeOpacity) else endColor.copy(alpha = safeOpacity), Offset(size.width * (0.35f + i * 0.13f), 0f), Offset(size.width * (0.05f + i * 0.13f), size.height), 2.dp.toPx())
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.22f), cornerRadius = CornerRadius(13.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          30 -> {
            drawRoundRect(brush = accent, topLeft = Offset(12.dp.toPx(), size.height - 8.dp.toPx()), size = Size(size.width - 24.dp.toPx(), 4.dp.toPx()), cornerRadius = CornerRadius(4.dp.toPx()))
            drawCircle(startColor.copy(alpha = safeOpacity), 4.dp.toPx(), Offset(15.dp.toPx(), 15.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 4.dp.toPx(), Offset(size.width - 15.dp.toPx(), 15.dp.toPx()))
          }
          31 -> {
            drawRoundRect(brush = accent, topLeft = Offset(4.dp.toPx(), 4.dp.toPx()), size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()), cornerRadius = CornerRadius(15.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
            drawLine(startColor.copy(alpha = safeOpacity), Offset(10.dp.toPx(), size.height * 0.5f), Offset(size.width - 10.dp.toPx(), size.height * 0.5f), 1.dp.toPx())
          }
          32 -> {
            drawCircle(startColor.copy(alpha = safeOpacity * 0.2f), size.minDimension * 0.45f, Offset(0f, size.height))
            drawCircle(endColor.copy(alpha = safeOpacity * 0.2f), size.minDimension * 0.35f, Offset(size.width, 0f))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(14.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
          }
          33 -> {
            drawRect(brush = Brush.horizontalGradient(listOf(startColor.copy(alpha = safeOpacity), Color.Transparent)), size = Size(size.width * 0.65f, size.height))
            drawLine(endColor.copy(alpha = safeOpacity), Offset(size.width - 5.dp.toPx(), 0f), Offset(size.width - 5.dp.toPx(), size.height), 3.dp.toPx())
          }
          34 -> {
            drawRoundRect(startColor.copy(alpha = safeOpacity * 0.14f), cornerRadius = CornerRadius(16.dp.toPx()))
            drawRoundRect(brush = accent, topLeft = Offset(5.dp.toPx(), 5.dp.toPx()), size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 3.dp.toPx(), Offset(15.dp.toPx(), size.height - 15.dp.toPx()))
          }
          35 -> {
            drawLine(startColor.copy(alpha = safeOpacity), Offset(0f, 0f), Offset(size.width * 0.45f, size.height), 5.dp.toPx())
            drawLine(endColor.copy(alpha = safeOpacity), Offset(size.width * 0.55f, 0f), Offset(size.width, size.height), 5.dp.toPx())
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.2f), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          36 -> {
            drawRoundRect(brush = accent, topLeft = Offset(0f, 0f), size = Size(size.width, 7.dp.toPx()), cornerRadius = CornerRadius(5.dp.toPx()))
            drawRoundRect(brush = accent, topLeft = Offset(0f, size.height - 7.dp.toPx()), size = Size(size.width, 7.dp.toPx()), cornerRadius = CornerRadius(5.dp.toPx()))
            drawCircle(Color.White.copy(alpha = safeOpacity * 0.55f), 2.dp.toPx(), Offset(size.width - 15.dp.toPx(), size.height * 0.5f))
          }
          37 -> {
            drawCircle(startColor.copy(alpha = safeOpacity * 0.18f), size.minDimension * 0.4f, Offset(size.width * 0.5f, size.height * 0.5f))
            drawRoundRect(brush = accent, topLeft = Offset(5.dp.toPx(), 5.dp.toPx()), size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()), cornerRadius = CornerRadius(13.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
          }
          38 -> {
            for (i in 0..3) drawRoundRect(if (i % 2 == 0) startColor.copy(alpha = safeOpacity) else endColor.copy(alpha = safeOpacity), topLeft = Offset(8.dp.toPx() + i * 7.dp.toPx(), 8.dp.toPx() + i * 5.dp.toPx()), size = Size(3.dp.toPx(), size.height - 16.dp.toPx() - i * 10.dp.toPx()), cornerRadius = CornerRadius(2.dp.toPx()))
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.2f), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          39 -> {
            drawRoundRect(brush = accent, topLeft = Offset(0f, size.height * 0.72f), size = Size(size.width, size.height * 0.28f), cornerRadius = CornerRadius(12.dp.toPx()))
            drawCircle(startColor.copy(alpha = safeOpacity), 5.dp.toPx(), Offset(size.width - 17.dp.toPx(), 17.dp.toPx()))
          }
          40 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(18.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.22f), topLeft = Offset(7.dp.toPx(), 7.dp.toPx()), size = Size(size.width - 14.dp.toPx(), size.height - 14.dp.toPx()), cornerRadius = CornerRadius(13.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 3.dp.toPx(), Offset(16.dp.toPx(), 16.dp.toPx()))
          }
          else -> {
            drawRect(
              brush = Brush.verticalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
              size = Size(accentWidth.toPx(), size.height),
            )
          }
        }
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

    CardAccentStyle.VIDEO_GLASS_PANEL -> {
      this.clip(shape).drawBehind {
        drawRoundRect(
          color = startColor.copy(alpha = safeOpacity * 0.13f),
          cornerRadius = CornerRadius(14.dp.toPx()),
        )
        drawRoundRect(
          brush = Brush.linearGradient(
            listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
          ),
          topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
          size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
          cornerRadius = CornerRadius(12.dp.toPx()),
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
        )
        drawLine(
          color = Color.White.copy(alpha = safeOpacity * 0.45f),
          start = Offset(12.dp.toPx(), 5.dp.toPx()),
          end = Offset(size.width - 12.dp.toPx(), 5.dp.toPx()),
          strokeWidth = 1.2.dp.toPx(),
        )
      }
    }

    CardAccentStyle.VIDEO_NEON_FRAME -> {
      this.clip(shape).drawBehind {
        drawRoundRect(
          color = startColor.copy(alpha = safeOpacity * 0.12f),
          cornerRadius = CornerRadius(14.dp.toPx()),
        )
        drawRoundRect(
          color = startColor.copy(alpha = safeOpacity),
          cornerRadius = CornerRadius(14.dp.toPx()),
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
        )
        drawRoundRect(
          color = endColor.copy(alpha = safeOpacity * 0.9f),
          topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
          size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
          cornerRadius = CornerRadius(10.dp.toPx()),
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2.dp.toPx()),
        )
      }
    }

    CardAccentStyle.VIDEO_SIDE_ACCENT -> {
      this.clip(shape).drawBehind {
        drawRect(
          brush = Brush.verticalGradient(
            listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
          ),
          size = Size(7.dp.toPx(), size.height),
        )
        drawRoundRect(
          brush = Brush.horizontalGradient(
            listOf(startColor.copy(alpha = safeOpacity * 0.75f), endColor.copy(alpha = safeOpacity * 0.75f)),
          ),
          topLeft = Offset(7.dp.toPx(), size.height - 4.dp.toPx()),
          size = Size(size.width - 7.dp.toPx(), 4.dp.toPx()),
          cornerRadius = CornerRadius(3.dp.toPx()),
        )
      }
    }

    CardAccentStyle.VIDEO_DIAGONAL -> {
      this.clip(shape).drawBehind {
        drawRoundRect(
          brush = Brush.linearGradient(
            listOf(startColor.copy(alpha = safeOpacity * 0.20f), endColor.copy(alpha = safeOpacity * 0.08f)),
          ),
          cornerRadius = CornerRadius(14.dp.toPx()),
        )
        drawLine(
          brush = Brush.linearGradient(
            listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
          ),
          start = Offset(size.width * 0.58f, 0f),
          end = Offset(size.width * 0.28f, size.height),
          strokeWidth = 3.dp.toPx(),
        )
        drawRoundRect(
          brush = Brush.linearGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))),
          cornerRadius = CornerRadius(14.dp.toPx()),
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
        )
      }
    }

    CardAccentStyle.VIDEO_LAYERED -> {
      this.clip(shape).drawBehind {
        drawRoundRect(
          color = startColor.copy(alpha = safeOpacity * 0.18f),
          cornerRadius = CornerRadius(15.dp.toPx()),
        )
        drawRoundRect(
          brush = Brush.linearGradient(
            listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
          ),
          cornerRadius = CornerRadius(15.dp.toPx()),
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
        )
        drawRoundRect(
          color = endColor.copy(alpha = safeOpacity * 0.55f),
          topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
          size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
          cornerRadius = CornerRadius(11.dp.toPx()),
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.3.dp.toPx()),
        )
        drawCircle(
          color = endColor.copy(alpha = safeOpacity),
          radius = 3.dp.toPx(),
          center = Offset(size.width - 13.dp.toPx(), 13.dp.toPx()),
        )
      }
    }

    in CardAccentStyle.videoStyles().filter { it.id in 11..20 } -> {
      this.clip(shape).drawBehind {
        val accent = Brush.linearGradient(
          listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
        )
        val radius = 14.dp.toPx()
        val inset = 4.dp.toPx()
        when (style.id) {
          11 -> drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(1.8.dp.toPx()))
          12 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawRoundRect(color = startColor.copy(alpha = safeOpacity * 0.18f), topLeft = Offset(inset, inset), size = Size(size.width - inset * 2, size.height - inset * 2), cornerRadius = CornerRadius(11.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          13 -> {
            drawLine(startColor.copy(alpha = safeOpacity), Offset(10.dp.toPx(), size.height - 5.dp.toPx()), Offset(size.width - 10.dp.toPx(), size.height - 5.dp.toPx()), 2.dp.toPx())
          }
          14 -> drawRoundRect(brush = Brush.horizontalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))), size = Size(size.width, 5.dp.toPx() + radius * 0.25f), cornerRadius = CornerRadius(radius))
          15 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(2.5.dp.toPx()))
            drawRoundRect(brush = accent, topLeft = Offset(5.dp.toPx(), 5.dp.toPx()), size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()), cornerRadius = CornerRadius(10.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          16 -> {
            drawRoundRect(color = startColor.copy(alpha = safeOpacity * 0.14f), cornerRadius = CornerRadius(radius))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
          }
          17 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(2.5.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 4.dp.toPx(), Offset(size.width - 13.dp.toPx(), 13.dp.toPx()))
          }
          18 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
            drawRoundRect(brush = Brush.verticalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity))), size = Size(5.dp.toPx(), size.height), cornerRadius = CornerRadius(radius))
          }
          19 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
            drawLine(endColor.copy(alpha = safeOpacity), Offset(0f, size.height * 0.75f), Offset(size.width, size.height * 0.25f), 2.dp.toPx())
          }
          20 -> {
            drawRoundRect(color = startColor.copy(alpha = safeOpacity * 0.12f), cornerRadius = CornerRadius(radius))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(radius), style = androidx.compose.ui.graphics.drawscope.Stroke(2.5.dp.toPx()))
            drawRoundRect(brush = accent, topLeft = Offset(6.dp.toPx(), 6.dp.toPx()), size = Size(size.width - 12.dp.toPx(), size.height - 12.dp.toPx()), cornerRadius = CornerRadius(9.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
        }
      }
    }

    in CardAccentStyle.videoStyles().filter { it.id in 41..60 } -> {
      this.clip(shape).drawBehind {
        val accent = Brush.linearGradient(
          listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
        )
        when (style.id) {
          41 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(14.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawLine(startColor.copy(alpha = safeOpacity), Offset(10.dp.toPx(), 0f), Offset(10.dp.toPx(), size.height), 2.dp.toPx())
          }
          42 -> {
            drawRoundRect(startColor.copy(alpha = safeOpacity * 0.13f), cornerRadius = CornerRadius(15.dp.toPx()))
            drawRoundRect(brush = accent, topLeft = Offset(4.dp.toPx(), 4.dp.toPx()), size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 4.dp.toPx(), Offset(size.width - 15.dp.toPx(), 15.dp.toPx()))
          }
          43 -> {
            drawRect(brush = Brush.horizontalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity * 0.15f))), size = Size(size.width, 5.dp.toPx()))
            drawRect(endColor.copy(alpha = safeOpacity), topLeft = Offset(0f, size.height - 3.dp.toPx()), size = Size(size.width, 3.dp.toPx()))
          }
          44 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(16.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(4.dp.toPx()))
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.3f), topLeft = Offset(7.dp.toPx(), 7.dp.toPx()), size = Size(size.width - 14.dp.toPx(), size.height - 14.dp.toPx()), cornerRadius = CornerRadius(10.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          45 -> {
            drawLine(startColor.copy(alpha = safeOpacity), Offset(0f, size.height * 0.25f), Offset(size.width, size.height * 0.25f), 3.dp.toPx())
            drawLine(endColor.copy(alpha = safeOpacity), Offset(0f, size.height * 0.75f), Offset(size.width, size.height * 0.75f), 3.dp.toPx())
            drawCircle(startColor.copy(alpha = safeOpacity), 4.dp.toPx(), Offset(size.width - 14.dp.toPx(), size.height * 0.5f))
          }
          46 -> {
            drawRect(brush = Brush.verticalGradient(listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity * 0.2f))), size = Size(6.dp.toPx(), size.height))
            drawRoundRect(brush = accent, topLeft = Offset(10.dp.toPx(), 5.dp.toPx()), size = Size(size.width - 15.dp.toPx(), size.height - 10.dp.toPx()), cornerRadius = CornerRadius(10.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
          }
          47 -> {
            drawCircle(startColor.copy(alpha = safeOpacity * 0.2f), size.minDimension * 0.4f, Offset(size.width, 0f))
            drawCircle(endColor.copy(alpha = safeOpacity * 0.75f), 7.dp.toPx(), Offset(size.width - 17.dp.toPx(), 17.dp.toPx()))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(13.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
          }
          48 -> {
            for (i in 0..3) drawLine(if (i % 2 == 0) startColor.copy(alpha = safeOpacity) else endColor.copy(alpha = safeOpacity), Offset(size.width * (0.2f + i * 0.2f), 0f), Offset(size.width * (0.2f + i * 0.2f), size.height), 1.5.dp.toPx())
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.2f), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          49 -> {
            drawRoundRect(brush = accent, topLeft = Offset(0f, size.height - 6.dp.toPx()), size = Size(size.width, 6.dp.toPx()), cornerRadius = CornerRadius(4.dp.toPx()))
            drawCircle(startColor.copy(alpha = safeOpacity), 3.dp.toPx(), Offset(14.dp.toPx(), 14.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 3.dp.toPx(), Offset(size.width - 14.dp.toPx(), 14.dp.toPx()))
          }
          50 -> {
            drawRoundRect(startColor.copy(alpha = safeOpacity * 0.12f), cornerRadius = CornerRadius(16.dp.toPx()))
            drawLine(startColor.copy(alpha = safeOpacity), Offset(0f, size.height), Offset(size.width, 0f), 3.dp.toPx())
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(16.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
          }
          51 -> {
            drawRoundRect(brush = accent, topLeft = Offset(5.dp.toPx(), 5.dp.toPx()), size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()), cornerRadius = CornerRadius(5.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawLine(endColor.copy(alpha = safeOpacity), Offset(0f, 0f), Offset(16.dp.toPx(), 0f), 4.dp.toPx())
          }
          52 -> {
            drawCircle(startColor.copy(alpha = safeOpacity * 0.22f), size.minDimension * 0.32f, Offset(size.width * 0.85f, size.height * 0.2f))
            drawRoundRect(brush = accent, topLeft = Offset(0f, size.height - 4.dp.toPx()), size = Size(size.width, 4.dp.toPx()), cornerRadius = CornerRadius(3.dp.toPx()))
            drawLine(endColor.copy(alpha = safeOpacity), Offset(12.dp.toPx(), 12.dp.toPx()), Offset(size.width - 12.dp.toPx(), 12.dp.toPx()), 1.dp.toPx())
          }
          53 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(18.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
            drawRoundRect(startColor.copy(alpha = safeOpacity * 0.16f), topLeft = Offset(6.dp.toPx(), 6.dp.toPx()), size = Size(size.width - 12.dp.toPx(), size.height - 12.dp.toPx()), cornerRadius = CornerRadius(12.dp.toPx()))
          }
          54 -> {
            drawRect(brush = Brush.verticalGradient(listOf(startColor.copy(alpha = safeOpacity), Color.Transparent)), size = Size(size.width, size.height * 0.45f))
            drawLine(endColor.copy(alpha = safeOpacity), Offset(0f, size.height - 4.dp.toPx()), Offset(size.width, size.height - 4.dp.toPx()), 2.dp.toPx())
          }
          55 -> {
            drawCircle(startColor.copy(alpha = safeOpacity), 5.dp.toPx(), Offset(15.dp.toPx(), 15.dp.toPx()))
            drawCircle(endColor.copy(alpha = safeOpacity), 5.dp.toPx(), Offset(size.width - 15.dp.toPx(), size.height - 15.dp.toPx()))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(13.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx()))
          }
          56 -> {
            drawRoundRect(brush = accent, topLeft = Offset(0f, 0f), size = Size(5.dp.toPx(), size.height), cornerRadius = CornerRadius(3.dp.toPx()))
            drawRoundRect(endColor.copy(alpha = safeOpacity), topLeft = Offset(5.dp.toPx(), 0f), size = Size(size.width - 5.dp.toPx(), 3.dp.toPx()), cornerRadius = CornerRadius(2.dp.toPx()))
            drawRoundRect(startColor.copy(alpha = safeOpacity), topLeft = Offset(5.dp.toPx(), size.height - 3.dp.toPx()), size = Size(size.width - 5.dp.toPx(), 3.dp.toPx()), cornerRadius = CornerRadius(2.dp.toPx()))
          }
          57 -> {
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(14.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawCircle(Color.White.copy(alpha = safeOpacity * 0.45f), 2.dp.toPx(), Offset(size.width * 0.5f, 8.dp.toPx()))
            drawCircle(Color.White.copy(alpha = safeOpacity * 0.45f), 2.dp.toPx(), Offset(size.width * 0.5f, size.height - 8.dp.toPx()))
          }
          58 -> {
            drawLine(startColor.copy(alpha = safeOpacity), Offset(size.width * 0.25f, 0f), Offset(0f, size.height * 0.5f), 3.dp.toPx())
            drawLine(endColor.copy(alpha = safeOpacity), Offset(size.width * 0.75f, size.height), Offset(size.width, size.height * 0.5f), 3.dp.toPx())
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.2f), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
          59 -> {
            drawRoundRect(brush = accent, topLeft = Offset(8.dp.toPx(), 0f), size = Size(size.width - 8.dp.toPx(), size.height), cornerRadius = CornerRadius(12.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(2.dp.toPx()))
            drawRect(startColor.copy(alpha = safeOpacity), size = Size(4.dp.toPx(), size.height))
          }
          60 -> {
            drawRoundRect(startColor.copy(alpha = safeOpacity * 0.13f), cornerRadius = CornerRadius(16.dp.toPx()))
            drawRoundRect(brush = accent, cornerRadius = CornerRadius(16.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(3.dp.toPx()))
            drawRoundRect(Color.White.copy(alpha = safeOpacity * 0.25f), topLeft = Offset(6.dp.toPx(), 6.dp.toPx()), size = Size(size.width - 12.dp.toPx(), size.height - 12.dp.toPx()), cornerRadius = CornerRadius(11.dp.toPx()), style = androidx.compose.ui.graphics.drawscope.Stroke(1.dp.toPx()))
          }
        }
      }
    }

      in CardAccentStyle.episodeStyles() -> {
        this.clip(shape).drawBehind {
          val accent = Brush.linearGradient(
            listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
          )
          val inset = 4.dp.toPx()
          val stroke = 2.5.dp.toPx()
          when (style.id) {
            61 -> {
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(20.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx()),
              )
              drawRect(
                color = endColor.copy(alpha = safeOpacity * 0.8f),
                topLeft = Offset(0f, size.height * 0.18f),
                size = Size(5.dp.toPx(), size.height * 0.64f),
              )
            }
            62 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.18f),
                cornerRadius = CornerRadius(18.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(inset, inset),
                size = Size(size.width - inset * 2, size.height - inset * 2),
                cornerRadius = CornerRadius(15.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
              drawLine(
                endColor.copy(alpha = safeOpacity * 0.7f),
                Offset(size.width * 0.12f, size.height * 0.78f),
                Offset(size.width * 0.88f, size.height * 0.78f),
                strokeWidth = 1.dp.toPx(),
              )
            }
            63 -> {
              drawRoundRect(
                brush = accent,
                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
                cornerRadius = CornerRadius(8.dp.toPx()),
              )
              drawRoundRect(
                color = Color.Black.copy(alpha = 0.48f * safeOpacity),
                topLeft = Offset(8.dp.toPx(), 8.dp.toPx()),
                size = Size(size.width - 16.dp.toPx(), size.height - 16.dp.toPx()),
                cornerRadius = CornerRadius(5.dp.toPx()),
              )
              drawRect(
                color = endColor.copy(alpha = safeOpacity),
                topLeft = Offset(8.dp.toPx(), size.height - 12.dp.toPx()),
                size = Size(size.width - 16.dp.toPx(), 4.dp.toPx()),
              )
            }
            64 -> {
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(18.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.45f),
                topLeft = Offset(7.dp.toPx(), 7.dp.toPx()),
                size = Size(size.width - 14.dp.toPx(), size.height - 14.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
              drawCircle(
                color = endColor.copy(alpha = safeOpacity),
                radius = 3.dp.toPx(),
                center = Offset(size.width - 12.dp.toPx(), 12.dp.toPx()),
              )
            }
            65 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.16f),
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(inset, inset),
                size = Size(size.width - inset * 2, size.height - inset * 2),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawLine(
                endColor.copy(alpha = safeOpacity),
                Offset(size.width * 0.18f, size.height - 7.dp.toPx()),
                Offset(size.width * 0.82f, size.height - 7.dp.toPx()),
                strokeWidth = 2.dp.toPx(),
              )
            }
            66 -> {
              drawRect(
                brush = accent,
                topLeft = Offset(0f, 0f),
                size = Size(5.dp.toPx(), size.height),
              )
              drawRect(
                color = endColor.copy(alpha = safeOpacity * 0.75f),
                topLeft = Offset(size.width - 5.dp.toPx(), 0f),
                size = Size(5.dp.toPx(), size.height),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(9.dp.toPx(), 4.dp.toPx()),
                size = Size(size.width - 18.dp.toPx(), size.height - 8.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
            }
            67 -> {
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              drawRect(
                color = Color.Black.copy(alpha = 0.22f * safeOpacity),
                topLeft = Offset(size.width / 2f, 0f),
                size = Size(size.width / 2f, size.height),
              )
              drawLine(
                Color.White.copy(alpha = 0.45f * safeOpacity),
                Offset(size.width / 2f, 8.dp.toPx()),
                Offset(size.width / 2f, size.height - 8.dp.toPx()),
                strokeWidth = 1.5.dp.toPx(),
              )
            }
            68 -> {
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(17.dp.toPx()),
              )
              drawRoundRect(
                color = Color.Black.copy(alpha = 0.16f * safeOpacity),
                topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
                size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
              )
              drawRoundRect(
                color = Color.White.copy(alpha = 0.55f * safeOpacity),
                topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
                size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
            }
            69 -> {
              val arm = 15.dp.toPx()
              val edge = Brush.linearGradient(
                listOf(startColor.copy(alpha = safeOpacity), endColor.copy(alpha = safeOpacity)),
              )
              drawLine(edge, Offset(3.dp.toPx(), arm), Offset(3.dp.toPx(), 3.dp.toPx()), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(3.dp.toPx(), 3.dp.toPx()), Offset(arm, 3.dp.toPx()), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(size.width - arm, 3.dp.toPx()), Offset(size.width - 3.dp.toPx(), 3.dp.toPx()), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(size.width - 3.dp.toPx(), 3.dp.toPx()), Offset(size.width - 3.dp.toPx(), arm), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(3.dp.toPx(), size.height - arm), Offset(3.dp.toPx(), size.height - 3.dp.toPx()), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(3.dp.toPx(), size.height - 3.dp.toPx()), Offset(arm, size.height - 3.dp.toPx()), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(size.width - arm, size.height - 3.dp.toPx()), Offset(size.width - 3.dp.toPx(), size.height - 3.dp.toPx()), strokeWidth = 3.dp.toPx())
              drawLine(edge, Offset(size.width - 3.dp.toPx(), size.height - arm), Offset(size.width - 3.dp.toPx(), size.height - 3.dp.toPx()), strokeWidth = 3.dp.toPx())
            }
            70 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.3f),
                topLeft = Offset(7.dp.toPx(), 7.dp.toPx()),
                size = Size(size.width - 7.dp.toPx(), size.height - 7.dp.toPx()),
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
                size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(16.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawRoundRect(
                color = endColor.copy(alpha = safeOpacity * 0.45f),
                topLeft = Offset(10.dp.toPx(), 10.dp.toPx()),
                size = Size(size.width - 20.dp.toPx(), size.height - 20.dp.toPx()),
                cornerRadius = CornerRadius(11.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
            }
            71 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.22f),
                cornerRadius = CornerRadius(18.dp.toPx()),
              )
              drawCircle(
                brush = accent,
                radius = size.minDimension * 0.34f,
                center = Offset(size.width * 0.5f, size.height * 0.5f),
              )
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(18.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
            }
            72 -> {
              drawRoundRect(
                color = Color(0xFF10131C).copy(alpha = safeOpacity * 0.82f),
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
              drawRect(
                brush = accent,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, 3.dp.toPx()),
              )
            }
            73 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.14f),
                cornerRadius = CornerRadius(15.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(0f, size.height * 0.68f),
                size = Size(size.width, size.height * 0.32f),
                cornerRadius = CornerRadius(0f),
              )
              drawRoundRect(
                color = Color.White.copy(alpha = safeOpacity * 0.18f),
                topLeft = Offset(8.dp.toPx(), 8.dp.toPx()),
                size = Size(size.width - 16.dp.toPx(), 3.dp.toPx()),
                cornerRadius = CornerRadius(3.dp.toPx()),
              )
            }
            74 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.12f),
                cornerRadius = CornerRadius(17.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
                cornerRadius = CornerRadius(14.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
              drawLine(
                Color.White.copy(alpha = safeOpacity * 0.55f),
                Offset(12.dp.toPx(), 5.dp.toPx()),
                Offset(size.width - 12.dp.toPx(), 5.dp.toPx()),
                strokeWidth = 1.dp.toPx(),
              )
            }
            75 -> {
              drawRoundRect(
                color = endColor.copy(alpha = safeOpacity * 0.32f),
                topLeft = Offset(9.dp.toPx(), 7.dp.toPx()),
                size = Size(size.width - 12.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                size = Size(size.width - 12.dp.toPx(), size.height - 12.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.5f),
                topLeft = Offset(7.dp.toPx(), 7.dp.toPx()),
                size = Size(size.width - 20.dp.toPx(), size.height - 20.dp.toPx()),
                cornerRadius = CornerRadius(8.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
            }
            76 -> {
              val band = size.width / 5f
              for (index in 0..4) {
                drawRect(
                  color = listOf(
                    Color(0xFFFF5252),
                    Color(0xFFFFC857),
                    Color(0xFF4DD0E1),
                    Color(0xFF7C83FD),
                    Color(0xFFE879F9),
                  )[index].copy(alpha = safeOpacity * 0.82f),
                  topLeft = Offset(index * band, 0f),
                  size = Size(band + 1f, 4.dp.toPx()),
                )
              }
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(15.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
            }
            77 -> {
              drawRect(
                brush = accent,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, 4.dp.toPx()),
              )
              drawRect(
                brush = accent,
                topLeft = Offset(0f, size.height - 4.dp.toPx()),
                size = Size(size.width, 4.dp.toPx()),
              )
              drawRoundRect(
                color = endColor.copy(alpha = safeOpacity * 0.35f),
                topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
                size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
            }
            78 -> {
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.16f),
                cornerRadius = CornerRadius(15.dp.toPx()),
              )
              drawLine(
                accent,
                Offset(size.width * 0.12f, size.height),
                Offset(size.width * 0.62f, 0f),
                strokeWidth = 5.dp.toPx(),
              )
              drawLine(
                Color.White.copy(alpha = safeOpacity * 0.45f),
                Offset(size.width * 0.2f, size.height),
                Offset(size.width * 0.7f, 0f),
                strokeWidth = 1.dp.toPx(),
              )
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(15.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
            }
            79 -> {
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(18.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4.dp.toPx()),
              )
              drawRoundRect(
                color = startColor.copy(alpha = safeOpacity * 0.2f),
                topLeft = Offset(7.dp.toPx(), 7.dp.toPx()),
                size = Size(size.width - 14.dp.toPx(), size.height - 14.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
              drawCircle(
                color = endColor.copy(alpha = safeOpacity * 0.8f),
                radius = 3.dp.toPx(),
                center = Offset(size.width - 13.dp.toPx(), 13.dp.toPx()),
              )
            }
            80 -> {
              drawRoundRect(
                color = Color(0xFF9AA4B2).copy(alpha = safeOpacity * 0.22f),
                cornerRadius = CornerRadius(15.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawLine(
                Color.White.copy(alpha = safeOpacity * 0.6f),
                Offset(12.dp.toPx(), 7.dp.toPx()),
                Offset(size.width - 12.dp.toPx(), 7.dp.toPx()),
                strokeWidth = 1.5.dp.toPx(),
              )
              drawLine(
                Color.Black.copy(alpha = safeOpacity * 0.22f),
                Offset(12.dp.toPx(), size.height - 7.dp.toPx()),
                Offset(size.width - 12.dp.toPx(), size.height - 7.dp.toPx()),
                strokeWidth = 1.dp.toPx(),
              )
            }
            81 -> {
              drawRoundRect(
                color = Color(0xFF7546A8).copy(alpha = safeOpacity * 0.24f),
                cornerRadius = CornerRadius(18.dp.toPx()),
              )
              drawCircle(
                brush = accent,
                radius = size.minDimension * 0.42f,
                center = Offset(size.width * 0.78f, size.height * 0.18f),
              )
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(18.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
            }
            82 -> {
              drawRoundRect(
                color = Color(0xFF006B83).copy(alpha = safeOpacity * 0.22f),
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              for (index in 0..2) {
                val y = size.height * (0.35f + index * 0.2f)
                drawLine(
                  accent,
                  Offset(8.dp.toPx(), y),
                  Offset(size.width - 8.dp.toPx(), y),
                  strokeWidth = (1.5f + index * 0.7f).dp.toPx(),
                )
              }
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(16.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
            }
            83 -> {
              drawRoundRect(
                color = Color(0xFF790D2D).copy(alpha = safeOpacity * 0.28f),
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              drawRect(
                brush = accent,
                topLeft = Offset(0f, size.height * 0.72f),
                size = Size(size.width, size.height * 0.28f),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
            }
            84 -> {
              drawRoundRect(
                color = Color(0xFF087A58).copy(alpha = safeOpacity * 0.2f),
                cornerRadius = CornerRadius(17.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
                size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawCircle(
                color = endColor.copy(alpha = safeOpacity),
                radius = 4.dp.toPx(),
                center = Offset(14.dp.toPx(), size.height - 14.dp.toPx()),
              )
            }
            85 -> {
              drawRoundRect(
                color = Color(0xFF8B633A).copy(alpha = safeOpacity * 0.2f),
                cornerRadius = CornerRadius(5.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
                size = Size(size.width - 10.dp.toPx(), size.height - 10.dp.toPx()),
                cornerRadius = CornerRadius(3.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
              drawLine(
                endColor.copy(alpha = safeOpacity),
                Offset(12.dp.toPx(), 12.dp.toPx()),
                Offset(size.width - 12.dp.toPx(), 12.dp.toPx()),
                strokeWidth = 1.dp.toPx(),
              )
            }
            86 -> {
              drawRoundRect(
                color = Color(0xFFB8E8F2).copy(alpha = safeOpacity * 0.2f),
                cornerRadius = CornerRadius(17.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()),
                cornerRadius = CornerRadius(14.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
              drawLine(
                Color.White.copy(alpha = safeOpacity * 0.75f),
                Offset(10.dp.toPx(), 8.dp.toPx()),
                Offset(size.width - 10.dp.toPx(), 8.dp.toPx()),
                strokeWidth = 1.5.dp.toPx(),
              )
            }
            87 -> {
              drawRoundRect(
                color = Color(0xFF20242B).copy(alpha = safeOpacity * 0.72f),
                cornerRadius = CornerRadius(12.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
                cornerRadius = CornerRadius(10.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5.dp.toPx()),
              )
              drawLine(
                Color.White.copy(alpha = safeOpacity * 0.25f),
                Offset(8.dp.toPx(), size.height * 0.5f),
                Offset(size.width - 8.dp.toPx(), size.height * 0.5f),
                strokeWidth = 1.dp.toPx(),
              )
            }
            88 -> {
              drawRoundRect(
                brush = Brush.linearGradient(
                  listOf(
                    Color(0xFF16A085).copy(alpha = safeOpacity * 0.5f),
                    Color(0xFF536DFE).copy(alpha = safeOpacity * 0.5f),
                    Color(0xFFB04CE0).copy(alpha = safeOpacity * 0.5f),
                  ),
                ),
                cornerRadius = CornerRadius(17.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()),
              )
            }
            89 -> {
              drawRoundRect(
                color = Color(0xFF241B12).copy(alpha = safeOpacity * 0.42f),
                cornerRadius = CornerRadius(16.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
                cornerRadius = CornerRadius(13.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawRoundRect(
                color = Color(0xFFFFE0A3).copy(alpha = safeOpacity * 0.7f),
                topLeft = Offset(9.dp.toPx(), 9.dp.toPx()),
                size = Size(size.width - 18.dp.toPx(), size.height - 18.dp.toPx()),
                cornerRadius = CornerRadius(9.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx()),
              )
            }
            90 -> {
              drawRoundRect(
                brush = Brush.linearGradient(
                  listOf(
                    startColor.copy(alpha = safeOpacity * 0.65f),
                    endColor.copy(alpha = safeOpacity * 0.65f),
                    Color(0xFF17152B).copy(alpha = safeOpacity * 0.8f),
                  ),
                ),
                cornerRadius = CornerRadius(19.dp.toPx()),
              )
              drawRoundRect(
                brush = accent,
                topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                size = Size(size.width - 8.dp.toPx(), size.height - 8.dp.toPx()),
                cornerRadius = CornerRadius(15.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3.dp.toPx()),
              )
              drawCircle(
                color = Color.White.copy(alpha = safeOpacity * 0.7f),
                radius = 2.dp.toPx(),
                center = Offset(size.width - 13.dp.toPx(), 13.dp.toPx()),
              )
            }
            else -> {
              drawRoundRect(
                brush = accent,
                cornerRadius = CornerRadius(18.dp.toPx()),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = stroke),
              )
            }
          }
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

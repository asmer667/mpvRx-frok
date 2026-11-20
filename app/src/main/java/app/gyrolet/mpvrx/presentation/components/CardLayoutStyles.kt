/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package app.gyrolet.mpvrx.presentation.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

enum class CardLayoutStyle(val id: Int, val nameAr: String, val nameEn: String) {
  GLOWING_EDGES(0, "حواف متوهجة", "Glowing Edges"),
  BOTTOM_TAB(1, "لسان سفلي", "Bottom Tab"),
  NEON_3D(2, "نيون ثلاثي الأبعاد", "3D Neon Frame"),
  AQUA_SPLIT(3, "تقسيم مائي", "Aqua Split"),
  FLAT_ORANGE(4, "برتقالي مسطح", "Flat Orange"),
  GLASS(5, "زجاجي", "Glassmorphism"),
  PINK_GRADIENT(6, "تدرج وردي", "Pink Gradient"),
  PINK_NEON(7, "نيون وردي", "Pink Neon"),
  SPORT(8, "رياضي", "Sporty Yellow/Red"),
  FOLDER_TAB(9, "لسان مجلد", "Folder Tab"),
  GRID(10, "شبكي", "Grid Pattern"),
  TORN_PAPER(11, "ورق ممزق", "Torn Paper"),
  SHADOW(12, "ظل عميق", "Shadow Depth"),
  SLIDER(13, "شريط منزلق", "Slider Look"),
  ASYMMETRIC(14, "زوايا غير متماثلة", "Asymmetrical Corners"),
  LAYERS(15, "طبقات متعددة", "Multi-layered"),
  METALLIC(16, "معدني", "Metallic Gradient");

  companion object {
    fun fromId(id: Int) = entries.firstOrNull { it.id == id } ?: GLOWING_EDGES
  }
}

fun Modifier.cardLayoutStyle(
  styleId: Int,
  opacity: Float = 1f,
  shape: Shape = RoundedCornerShape(16.dp),
): Modifier {
  val style = CardLayoutStyle.fromId(styleId)
  val alpha = opacity.coerceIn(0f, 1f)
  val navy = Color(0xFF101827)
  val charcoal = Color(0xFF202B3B)
  val palettes = listOf(
    listOf(Color(0xFF00E5FF), Color(0xFF536DFE)),
    listOf(Color(0xFF26364D), Color(0xFF00BFA5)),
    listOf(Color(0xFF00E5FF), Color(0xFF7C4DFF)),
    listOf(Color(0xFF00E5FF), Color(0xFF1565C0)),
    listOf(Color(0xFFFF9800), Color(0xFFFF5722)),
    listOf(Color(0xFF455A64), Color(0xFF90A4AE)),
    listOf(Color(0xFFFF4081), Color(0xFF7C4DFF)),
    listOf(Color(0xFFFF4081), Color(0xFFE040FB)),
    listOf(Color(0xFFFFD600), Color(0xFFFF1744)),
    listOf(Color(0xFF536DFE), Color(0xFF00BFA5)),
    listOf(Color(0xFF26364D), Color(0xFF536DFE)),
    listOf(Color(0xFF795548), Color(0xFFFFCC80)),
    listOf(Color(0xFF263238), Color(0xFF607D8B)),
    listOf(Color(0xFF00BFA5), Color(0xFF536DFE)),
    listOf(Color(0xFF7C4DFF), Color(0xFF00E5FF)),
    listOf(Color(0xFF26364D), Color(0xFF536DFE)),
    listOf(Color(0xFF90A4AE), Color(0xFFECEFF1)),
  )
  val colors = palettes[style.id]
  val gradient = Brush.linearGradient(colors)
  return clip(shape).drawBehind {
    val w = size.width
    val h = size.height
    val radius = 18.dp.toPx()
    drawContext.canvas.saveLayer(
      Rect(0f, 0f, w, h),
      Paint().apply { this.alpha = alpha },
    )
    drawRoundRect(
      brush = Brush.linearGradient(listOf(navy, charcoal)),
      cornerRadius = CornerRadius(radius, radius),
    )
    when (style) {
      CardLayoutStyle.GLOWING_EDGES, CardLayoutStyle.PINK_NEON,
      CardLayoutStyle.NEON_3D -> {
        drawRoundRect(gradient, cornerRadius = CornerRadius(radius, radius),
          style = Stroke(3.dp.toPx()))
        if (style == CardLayoutStyle.NEON_3D) {
          drawRoundRect(colors.last().copy(alpha = .35f),
            topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
            size = Size(w - 10.dp.toPx(), h - 10.dp.toPx()),
            cornerRadius = CornerRadius(radius, radius), style = Stroke(2.dp.toPx()))
        }
      }
      CardLayoutStyle.BOTTOM_TAB, CardLayoutStyle.SLIDER -> {
        drawRect(gradient, Offset(0f, h - 7.dp.toPx()), Size(w, 7.dp.toPx()))
        drawRoundRect(colors.first().copy(alpha = 1f),
          Offset(w * .65f, h - 12.dp.toPx()), Size(w * .25f, 12.dp.toPx()),
          CornerRadius(8.dp.toPx(), 8.dp.toPx()))
      }
      CardLayoutStyle.AQUA_SPLIT, CardLayoutStyle.FLAT_ORANGE,
      CardLayoutStyle.PINK_GRADIENT, CardLayoutStyle.SPORT -> {
        drawRect(gradient, size = Size(w * .12f, h))
        drawRect(colors.first().copy(alpha = .13f), Offset(w * .12f, 0f),
          Size(w * .88f, h))
        if (style == CardLayoutStyle.SPORT) {
          drawLine(colors.first(), Offset(w * .72f, 0f), Offset(w, h),
            7.dp.toPx())
        }
      }
      CardLayoutStyle.GLASS -> {
        drawRoundRect(Color.White.copy(alpha = .10f),
          cornerRadius = CornerRadius(radius, radius))
        drawRoundRect(Color.White.copy(alpha = .35f),
          cornerRadius = CornerRadius(radius, radius), style = Stroke(1.dp.toPx()))
      }
      CardLayoutStyle.FOLDER_TAB -> {
        drawRoundRect(gradient, Offset(14.dp.toPx(), 0f),
          Size(w * .36f, 9.dp.toPx()), CornerRadius(7.dp.toPx(), 7.dp.toPx()))
        drawRoundRect(colors.first().copy(alpha = .18f),
          cornerRadius = CornerRadius(radius, radius), style = Stroke(2.dp.toPx()))
      }
      CardLayoutStyle.GRID -> {
        val step = 18.dp.toPx()
        var x = 0f
        while (x < w) {
          drawLine(colors.first().copy(alpha = .22f),
            Offset(x, 0f), Offset(x, h), 1.dp.toPx())
          x += step
        }
        var y = 0f
        while (y < h) {
          drawLine(colors.last().copy(alpha = .22f),
            Offset(0f, y), Offset(w, y), 1.dp.toPx())
          y += step
        }
      }
      CardLayoutStyle.TORN_PAPER -> {
        drawRect(colors.first().copy(alpha = .18f), size = Size(w, 8.dp.toPx()))
        drawLine(colors.last().copy(alpha = 1f), Offset(0f, 8.dp.toPx()),
          Offset(w, 8.dp.toPx()), 2.dp.toPx())
      }
      CardLayoutStyle.SHADOW -> {
        drawRoundRect(Color.Black.copy(alpha = .30f),
          topLeft = Offset(0f, 5.dp.toPx()), cornerRadius = CornerRadius(radius, radius))
        drawRoundRect(gradient, cornerRadius = CornerRadius(radius, radius),
          style = Stroke(1.5.dp.toPx()))
      }
      CardLayoutStyle.ASYMMETRIC -> {
        drawRoundRect(gradient, cornerRadius = CornerRadius(30.dp.toPx(), 8.dp.toPx()),
          style = Stroke(3.dp.toPx()))
      }
      CardLayoutStyle.LAYERS -> {
        drawRoundRect(colors.first().copy(alpha = .45f),
          topLeft = Offset(5.dp.toPx(), 5.dp.toPx()),
          size = Size(w - 10.dp.toPx(), h - 10.dp.toPx()),
          cornerRadius = CornerRadius(radius, radius))
        drawRoundRect(gradient, cornerRadius = CornerRadius(radius, radius),
          style = Stroke(2.dp.toPx()))
      }
      CardLayoutStyle.METALLIC -> {
        drawRect(Brush.verticalGradient(listOf(
          Color(0xFF607D8B), Color(0xFF263238), Color(0xFFB0BEC5),
        )), size = Size(w, h), alpha = .45f)
        drawRoundRect(gradient, cornerRadius = CornerRadius(radius, radius),
          style = Stroke(2.dp.toPx()))
      }
    }
    drawContext.canvas.restore()
  }
}

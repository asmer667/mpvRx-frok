/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package app.gyrolet.mpvrx.presentation.components

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

enum class CardLayoutStyle(val id: Int, val nameAr: String, val nameEn: String) {
  GLOWING_EDGES(0, "حواف متوهجة", "Glowing Edges"),
  BOTTOM_TAB(1, "لسان سفلي", "Bottom Tab"),
  NEON_3D(2, "إطار نيون ثلاثي الأبعاد", "3D Neon Frame"),
  AQUA_SPLIT(3, "بطاقة مائية مقسمة", "Aqua Split Panel"),
  FLAT_ORANGE(4, "لوحة جانبية برتقالية", "Orange Side Panel"),
  GLASS(5, "زجاج بلوري متعدد الأوجه", "Faceted Crystal Glass"),
  PINK_GRADIENT(6, "تدرج وردي منساب", "Flowing Pink Gradient"),
  PINK_NEON(7, "توهج داخلي نيون", "Inner Neon Glow"),
  SPORT(8, "خطوط رياضية نشطة", "Sport Racing Stripes"),
  FOLDER_TAB(9, "لسان علوي بارز", "Raised Folder Tab"),
  GRID(10, "شبكة مضيئة", "Illuminated Grid"),
  TORN_PAPER(11, "قصاصة ورقية ممزقة", "Torn Paper Edge"),
  SHADOW(12, "ظل عميق متعدد الطبقات", "Deep Shadow Layers"),
  SLIDER(13, "شريط تحكم باللمس", "Touch Slider"),
  ASYMMETRIC(14, "زوايا غير متماثلة", "Asymmetric Corners"),
  LAYERS(15, "طبقات متراكبة", "Stacked Layers"),
  METALLIC(16, "زجاج معدني عاكس", "Reflective Metallic"),
  CAMERA(17, "تأثير عدسة الكاميرا", "Camera Lens"),
  PAPER_FOLD(18, "ورق مطوي بحافة مقصوصة", "Folded Paper Cutout"),
  LIQUID_GLASS(19, "زجاج سائل", "Liquid Glass");

  companion object {
    fun fromId(id: Int) = entries.firstOrNull { it.id == id } ?: GLOWING_EDGES
  }
}

private fun layoutPath(id: Int, w: Float, h: Float): Path = Path().apply {
  when (id) {
    1 -> { moveTo(18f, 0f); lineTo(w-18f,0f); quadraticTo(w,0f,w,h*.22f); lineTo(w,h*.78f); quadraticTo(w,h,w-18f,h); lineTo(18f,h); quadraticTo(0f,h,0f,h*.78f); lineTo(0f,18f); quadraticTo(0f,0f,18f,0f); close() }
    2 -> { moveTo(18f,0f); lineTo(w-28f,0f); lineTo(w,h*.18f); lineTo(w,h-18f); lineTo(w-18f,h); lineTo(18f,h); lineTo(0f,h-18f); lineTo(0f,18f); close() }
    3 -> { moveTo(0f,18f); quadraticTo(0f,0f,18f,0f); lineTo(w*.70f,0f); lineTo(w*.70f,h); lineTo(18f,h); quadraticTo(0f,h,0f,h-18f); close() }
    4 -> { moveTo(18f,0f); lineTo(w-18f,0f); quadraticTo(w,0f,w,18f); lineTo(w,h-18f); quadraticTo(w,h,w-18f,h); lineTo(18f,h); quadraticTo(0f,h,0f,h-18f); lineTo(0f,18f); quadraticTo(0f,0f,18f,0f); close() }
    5 -> { moveTo(0f,18f); lineTo(w*.12f,0f); lineTo(w*.82f,0f); lineTo(w,h*.20f); lineTo(w,h*.80f); lineTo(w*.84f,h); lineTo(w*.12f,h); lineTo(0f,h*.82f); close() }
    6 -> { moveTo(18f,0f); lineTo(w-18f,0f); quadraticTo(w,0f,w,18f); lineTo(w,h-26f); quadraticTo(w,h,w-26f,h); lineTo(24f,h); quadraticTo(0f,h,0f,h-24f); lineTo(0f,18f); quadraticTo(0f,0f,18f,0f); close() }
    8 -> { moveTo(0f,0f); lineTo(w-24f,0f); lineTo(w,h*.28f); lineTo(w-18f,h); lineTo(0f,h); close() }
    9 -> { moveTo(12f,0f); lineTo(w*.42f,0f); lineTo(w*.48f,10f); lineTo(w-14f,10f); quadraticTo(w,10f,w,24f); lineTo(w,h-14f); quadraticTo(w,h,w-14f,h); lineTo(14f,h); quadraticTo(0f,h,0f,h-14f); lineTo(0f,14f); quadraticTo(0f,0f,12f,0f); close() }
    11 -> { moveTo(16f,0f); lineTo(w-16f,0f); lineTo(w,h-14f); for (i in 10 downTo 0) { val x=w*i/10f; lineTo(x,h-(if(i%2==0) 2f else 13f)) }; lineTo(0f,16f); quadraticTo(0f,0f,16f,0f); close() }
    13 -> { moveTo(18f,0f); lineTo(w-18f,0f); quadraticTo(w,0f,w,18f); lineTo(w,h-18f); quadraticTo(w,h,w-18f,h); lineTo(18f,h); quadraticTo(0f,h,0f,h-18f); lineTo(0f,18f); quadraticTo(0f,0f,18f,0f); close() }
    14 -> { moveTo(34f,0f); lineTo(w-12f,0f); quadraticTo(w,0f,w,12f); lineTo(w,h-34f); lineTo(w-34f,h); lineTo(12f,h); quadraticTo(0f,h,0f,h-12f); lineTo(0f,34f); close() }
    17 -> { moveTo(18f,0f); lineTo(w-18f,0f); quadraticTo(w,0f,w,18f); lineTo(w,h-18f); quadraticTo(w,h,w-18f,h); lineTo(18f,h); quadraticTo(0f,h,0f,h-18f); lineTo(0f,18f); quadraticTo(0f,0f,18f,0f); close() }
    18 -> { moveTo(0f,0f); lineTo(w-28f,0f); lineTo(w,h*.20f); lineTo(w,h); lineTo(0f,h); close() }
    19 -> { moveTo(24f,0f); cubicTo(w*.25f,h*.12f,w*.72f,-h*.08f,w-18f,10f); cubicTo(w+10f,h*.25f,w-8f,h*.75f,w-18f,h-10f); cubicTo(w*.65f,h*1.08f,w*.28f,h*.88f,18f,h); cubicTo(-10f,h*.72f,10f,h*.28f,24f,0f); close() }
    else -> { moveTo(18f,0f); lineTo(w-18f,0f); quadraticTo(w,0f,w,18f); lineTo(w,h-18f); quadraticTo(w,h,w-18f,h); lineTo(18f,h); quadraticTo(0f,h,0f,h-18f); lineTo(0f,18f); quadraticTo(0f,0f,18f,0f); close() }
  }
}

private fun layoutShape(id: Int): Shape = GenericShape { size, _ ->
  addPath(layoutPath(id, size.width, size.height))
}

fun Modifier.cardLayoutStyle(
  styleId: Int,
  opacity: Float = 1f,
  shape: Shape = RoundedCornerShape(16.dp),
): Modifier {
  val style = CardLayoutStyle.fromId(styleId)
  val alpha = opacity.coerceIn(0f, 1f)
  val colors = when (style) {
    CardLayoutStyle.BOTTOM_TAB -> listOf(Color(0xFFFF4FA3), Color(0xFF19C9F2))
    CardLayoutStyle.NEON_3D -> listOf(Color(0xFF38F7FF), Color(0xFFFF42D0))
    CardLayoutStyle.AQUA_SPLIT -> listOf(Color(0xFF168CFF), Color(0xFF7D38C8))
    CardLayoutStyle.FLAT_ORANGE -> listOf(Color(0xFFFF6A00), Color(0xFFFFB52E))
    CardLayoutStyle.GLASS -> listOf(Color(0xFFE9F8FF), Color(0xFF7CA7D8))
    CardLayoutStyle.PINK_GRADIENT, CardLayoutStyle.PINK_NEON -> listOf(Color(0xFFFF3B9D), Color(0xFF813CFF))
    CardLayoutStyle.SPORT -> listOf(Color(0xFFFFD400), Color(0xFFFF3B20))
    CardLayoutStyle.TORN_PAPER, CardLayoutStyle.PAPER_FOLD -> listOf(Color(0xFFFFF2D1), Color(0xFFD7B98A))
    CardLayoutStyle.METALLIC -> listOf(Color(0xFFF7FCFF), Color(0xFF71879B), Color(0xFFE7F4FF))
    CardLayoutStyle.CAMERA -> listOf(Color(0xFFFFA46B), Color(0xFF9B62FF), Color(0xFF55D9FF))
    CardLayoutStyle.LIQUID_GLASS -> listOf(Color(0xFFB8F5FF), Color(0xFF8C76FF), Color(0xFFFFB7E8))
    else -> listOf(Color(0xFF00E5FF), Color(0xFF536DFE))
  }
  val shapeForStyle = if (style == CardLayoutStyle.GLASS || style == CardLayoutStyle.TORN_PAPER ||
    style == CardLayoutStyle.NEON_3D || style == CardLayoutStyle.BOTTOM_TAB ||
    style == CardLayoutStyle.AQUA_SPLIT || style == CardLayoutStyle.FLAT_ORANGE ||
    style == CardLayoutStyle.SPORT || style == CardLayoutStyle.FOLDER_TAB ||
    style == CardLayoutStyle.ASYMMETRIC || style == CardLayoutStyle.PAPER_FOLD ||
    style == CardLayoutStyle.LIQUID_GLASS) layoutShape(style.id) else shape

  return clip(shapeForStyle).drawBehind {
    val w = size.width
    val h = size.height
    val p = layoutPath(style.id, w, h)
    val gradient = Brush.linearGradient(colors, Offset.Zero, Offset(w, h))
    val dark = Brush.linearGradient(listOf(Color(0xFF111827), Color(0xFF26334A)))
    drawContext.canvas.saveLayer(androidx.compose.ui.geometry.Rect(0f, 0f, w, h),
      androidx.compose.ui.graphics.Paint().apply { this.alpha = alpha })
    drawPath(p, dark)
    when (style) {
      CardLayoutStyle.GLOWING_EDGES, CardLayoutStyle.PINK_NEON -> {
        drawPath(p, gradient, style = Stroke(3.5.dp.toPx()))
        drawPath(p, colors.first().copy(alpha = .12f))
        drawPath(p, colors.first().copy(alpha = .75f), style = Stroke(1.dp.toPx()))
      }
      CardLayoutStyle.BOTTOM_TAB -> {
        drawPath(p, dark)
        val tab = Path().apply { moveTo(w*.14f,h*.72f); quadraticTo(w*.14f,h*.94f,w*.30f,h*.94f); lineTo(w*.82f,h*.94f); quadraticTo(w*.94f,h*.94f,w*.94f,h*.72f); lineTo(w*.14f,h*.72f); close() }
        drawPath(tab, gradient)
      }
      CardLayoutStyle.NEON_3D -> {
        drawPath(p, gradient, style = Stroke(4.dp.toPx()))
        drawRoundRect(colors.last().copy(alpha=.48f), Offset(7.dp.toPx(), 7.dp.toPx()),
          Size(w - 14.dp.toPx(), h - 14.dp.toPx()), CornerRadius(14.dp.toPx()),
          style = Stroke(2.dp.toPx()))
        drawLine(colors.first(), Offset(12f,h-10f), Offset(w*.35f,h-10f), 3.dp.toPx())
      }
      CardLayoutStyle.AQUA_SPLIT -> {
        drawPath(p, dark)
        drawRect(Brush.linearGradient(colors), size=Size(w*.30f,h))
        drawCircle(Color.White.copy(alpha=.25f), h*.35f, Offset(w*.16f,h*.5f))
      }
      CardLayoutStyle.FLAT_ORANGE -> {
        drawPath(p,dark)
        drawRect(Brush.horizontalGradient(colors), Offset(w*.78f,0f), Size(w*.22f,h))
      }
      CardLayoutStyle.GLASS -> {
        drawPath(p, Brush.linearGradient(listOf(Color(0xFFB9E8FF).copy(alpha=.42f), Color(0xFF7B88D8).copy(alpha=.18f), Color.White.copy(alpha=.30f))))
        val facets = listOf(
          listOf(0f,0f,w*.34f,0f,w*.20f,h*.55f), listOf(w*.34f,0f,w*.72f,0f,w*.52f,h*.58f),
          listOf(w*.72f,0f,w,h*.20f,w*.82f,h*.64f), listOf(0f,h*.55f,w*.20f,h*.55f,w*.42f,h),
          listOf(w*.20f,h*.55f,w*.82f,h*.64f,w*.42f,h), listOf(w*.82f,h*.64f,w,h*.20f,w,h),
        )
        facets.forEachIndexed { i, a -> val fp=Path().apply { moveTo(a[0],a[1]); lineTo(a[2],a[3]); lineTo(a[4],a[5]); close() }; drawPath(fp, if(i%2==0) Color.White.copy(alpha=.20f) else colors.last().copy(alpha=.12f)) }
        drawPath(p, Color.White.copy(alpha=.65f), style=Stroke(1.5.dp.toPx()))
      }
      CardLayoutStyle.PINK_GRADIENT -> {
        drawPath(p, Brush.linearGradient(colors))
        drawOval(Color.White.copy(alpha=.12f), Offset(-w*.15f,-h*.35f), Size(w*.95f,h*.95f))
        drawRect(Color(0xFF141421).copy(alpha=.22f), Offset(0f,h*.72f), Size(w,h*.28f))
      }
      CardLayoutStyle.PINK_NEON -> {
        drawPath(p, Color.Black)
        drawPath(p, gradient, style=Stroke(4.dp.toPx()))
        drawPath(p, colors.first().copy(alpha=.08f))
      }
      CardLayoutStyle.SPORT -> {
        drawPath(p, Brush.linearGradient(listOf(Color(0xFFFFD600),Color(0xFFFF4D1F))))
        for (i in 0..3) { val x=w*(.08f+i*.10f); val stripe=Path().apply { moveTo(x,h); lineTo(x+w*.07f,0f); lineTo(x+w*.12f,0f); lineTo(x+w*.05f,h); close() }; drawPath(stripe, Color(0xFF5B1520).copy(alpha=.72f)) }
      }
      CardLayoutStyle.FOLDER_TAB -> {
        drawPath(p,dark)
        val tab=Path().apply { moveTo(w*.08f,h*.18f); lineTo(w*.38f,h*.18f); lineTo(w*.44f,h*.28f); lineTo(w*.08f,h*.28f); close() }
        drawPath(tab,gradient); drawLine(colors.first(),Offset(0f,h*.30f),Offset(w,h*.30f),2.dp.toPx())
      }
      CardLayoutStyle.GRID -> {
        drawPath(p,dark); val step=16.dp.toPx(); var x=0f
        while(x<w){ drawLine(colors.first().copy(alpha=.32f),Offset(x,0f),Offset(x,h),1.dp.toPx()); x+=step }
        var y=0f; while(y<h){ drawLine(colors.last().copy(alpha=.32f),Offset(0f,y),Offset(w,y),1.dp.toPx()); y+=step }
        drawPath(p,colors.first().copy(alpha=.85f),style=Stroke(1.5.dp.toPx()))
      }
      CardLayoutStyle.TORN_PAPER -> {
        drawPath(p,Brush.verticalGradient(listOf(Color(0xFFFFF7E6),Color(0xFFE4D1AE))))
        drawLine(Color(0xFFB18B56),Offset(0f,h*.20f),Offset(w,h*.20f),1.dp.toPx())
        for(i in 0..4) drawLine(Color(0xFFB99B70).copy(alpha=.45f),Offset(w*.12f,h*(.42f+i*.10f)),Offset(w*.82f,h*(.42f+i*.10f)),1.dp.toPx())
      }
      CardLayoutStyle.SHADOW -> {
        drawRoundRect(Color.Black.copy(alpha=.50f),Offset(5.dp.toPx(),7.dp.toPx()),Size(w-5.dp.toPx(),h-5.dp.toPx()),CornerRadius(22.dp.toPx()))
        drawPath(p,Brush.linearGradient(listOf(Color(0xFF322039),Color(0xFF111827))))
        drawPath(p,Brush.linearGradient(colors),style=Stroke(2.dp.toPx()))
        drawRoundRect(colors.first().copy(alpha=.12f),Offset(8.dp.toPx(),8.dp.toPx()),Size(w-16.dp.toPx(),h-16.dp.toPx()),CornerRadius(18.dp.toPx()))
      }
      CardLayoutStyle.SLIDER -> {
        drawPath(p,dark); drawRoundRect(Color.White.copy(alpha=.12f),Offset(w*.08f,h*.72f),Size(w*.84f,h*.13f),CornerRadius(h*.08f))
        drawRoundRect(Brush.horizontalGradient(colors),Offset(w*.08f,h*.72f),Size(w*.58f,h*.13f),CornerRadius(h*.08f))
        drawCircle(Color.White, h*.10f, Offset(w*.66f,h*.785f)); drawCircle(colors.last(),h*.065f,Offset(w*.66f,h*.785f))
      }
      CardLayoutStyle.ASYMMETRIC -> { drawPath(p,dark); drawPath(p,gradient,style=Stroke(3.dp.toPx())); drawRect(colors.last(),Offset(w*.90f,0f),Size(w*.10f,h)) }
      CardLayoutStyle.LAYERS -> {
        drawRoundRect(colors.first().copy(alpha=.25f),Offset(10.dp.toPx(),8.dp.toPx()),Size(w-10.dp.toPx(),h-8.dp.toPx()),CornerRadius(20.dp.toPx()))
        drawRoundRect(colors.last().copy(alpha=.30f),Offset(5.dp.toPx(),4.dp.toPx()),Size(w-10.dp.toPx(),h-8.dp.toPx()),CornerRadius(20.dp.toPx()))
        drawPath(p,Brush.linearGradient(colors)); drawPath(p,Color.White.copy(alpha=.55f),style=Stroke(1.dp.toPx()))
      }
      CardLayoutStyle.METALLIC -> {
        drawPath(p,Brush.linearGradient(listOf(Color(0xFF34495E),Color(0xFFEAF4FA),Color(0xFF71879B),Color(0xFFFFFFFF),Color(0xFF455A64))))
        drawRect(Brush.linearGradient(listOf(Color.Transparent,Color.White.copy(alpha=.72f),Color.Transparent)),Offset(w*.10f,0f),Size(w*.22f,h))
        drawPath(p,Color.White.copy(alpha=.8f),style=Stroke(1.5.dp.toPx()))
      }
      CardLayoutStyle.CAMERA -> {
        drawPath(p,Brush.linearGradient(listOf(Color(0xFF121827),Color(0xFF34244C))))
        val r=h*.34f; val c=Offset(w*.86f,h*.50f)
        drawCircle(Color(0xFF1A2337),r*1.42f,c); drawCircle(Brush.sweepGradient(colors),r*1.18f,c,style=Stroke(5.dp.toPx()))
        drawCircle(Color(0xFF080D19),r*.88f,c); drawCircle(Brush.radialGradient(listOf(Color(0xFF63DFFF),Color(0xFF273C6D),Color(0xFF080D19)),c,r),r*.72f,c)
        drawCircle(Color.White.copy(alpha=.5f),r*.16f,Offset(c.x-r*.28f,c.y-r*.30f))
      }
      CardLayoutStyle.PAPER_FOLD -> {
        drawPath(p,Brush.linearGradient(listOf(Color(0xFFFFF6E1),Color(0xFFE6D3AA))))
        val fold=Path().apply { moveTo(w*.72f,0f); lineTo(w, h*.20f); lineTo(w*.72f,h*.20f); close() }; drawPath(fold,Color(0xFFC8AE7D))
        drawLine(Color(0xFFB99B70),Offset(w*.72f,0f),Offset(w*.72f,h*.20f),1.dp.toPx())
        for(i in 0..3) drawLine(Color(0xFFB99B70).copy(alpha=.55f),Offset(w*.12f,h*(.42f+i*.11f)),Offset(w*.65f,h*(.42f+i*.11f)),1.dp.toPx())
      }
      CardLayoutStyle.LIQUID_GLASS -> {
        drawPath(p,Brush.linearGradient(listOf(Color(0xFFB8F5FF).copy(alpha=.48f),Color(0xFF927CFF).copy(alpha=.36f),Color(0xFFFFB7E8).copy(alpha=.42f))))
        drawOval(Color.White.copy(alpha=.22f),Offset(w*.08f,h*.04f),Size(w*.75f,h*.34f))
        drawPath(p,Brush.linearGradient(listOf(Color.White.copy(alpha=.8f),Color.Transparent,colors.last().copy(alpha=.8f))),style=Stroke(2.dp.toPx()))
        drawLine(Color.White.copy(alpha=.55f),Offset(w*.08f,h*.18f),Offset(w*.72f,h*.18f),1.dp.toPx())
      }
    }
    drawContext.canvas.restore()
  }
}

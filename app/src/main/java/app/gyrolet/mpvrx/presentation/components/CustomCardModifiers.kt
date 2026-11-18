package app.gyrolet.mpvrx.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun getDynamicCardModifier(
    styleIndex: Int,
    opacity: Float,
    isFolder: Boolean
): Modifier {
    val baseColor = Color(0xFF1E1E2E).copy(alpha = opacity)
    val accentColor = if (isFolder) Color.Cyan else Color.Magenta

    return when (styleIndex) {
        1 -> Modifier.background(Brush.linearGradient(listOf(baseColor, baseColor.copy(alpha = opacity * 0.5f))), RoundedCornerShape(16.dp))
        2 -> Modifier.background(Color.Black.copy(alpha = opacity)).border(1.dp, Brush.sweepGradient(listOf(accentColor, Color.Blue)), RoundedCornerShape(20.dp))
        3 -> Modifier.background(baseColor, RoundedCornerShape(4.dp))
        4 -> Modifier.background(baseColor, RoundedCornerShape(32.dp))
        5 -> Modifier.background(baseColor).border(1.5.dp, accentColor.copy(alpha = 0.8f), RoundedCornerShape(16.dp))
        6 -> Modifier.background(baseColor).border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
        7 -> Modifier.background(Brush.radialGradient(listOf(baseColor, Color.DarkGray)), RoundedCornerShape(18.dp))
        8 -> Modifier.background(Color.Transparent).border(1.5.dp, baseColor, RoundedCornerShape(14.dp))
        9 -> Modifier.background(Brush.horizontalGradient(listOf(baseColor, Color.Transparent)), RoundedCornerShape(16.dp))
        10 -> Modifier.background(baseColor.copy(alpha = opacity * 0.9f), RoundedCornerShape(24.dp))
        11 -> Modifier.background(Color.Black.copy(alpha = opacity)).border(1.dp, baseColor, RoundedCornerShape(10.dp))
        12 -> Modifier.background(baseColor, RoundedCornerShape(topStart = 22.dp, bottomEnd = 22.dp))
        13 -> Modifier.background(Brush.verticalGradient(listOf(baseColor, Color.Black)), RoundedCornerShape(16.dp))
        14 -> Modifier.background(baseColor).border(3.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
        15 -> Modifier.background(baseColor.copy(alpha = opacity * 0.35f), RoundedCornerShape(16.dp)).border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
        16 -> Modifier.background(baseColor, RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp))
        17 -> Modifier.background(Brush.linearGradient(listOf(Color.Gray, baseColor)), RoundedCornerShape(16.dp))
        18 -> Modifier.background(baseColor).border(2.dp, Color.Yellow.copy(alpha = 0.6f), RoundedCornerShape(18.dp))
        19 -> Modifier.background(Color.DarkGray.copy(alpha = opacity), RoundedCornerShape(12.dp)).border(1.dp, baseColor, RoundedCornerShape(12.dp))
        else -> Modifier.background(baseColor, RoundedCornerShape(16.dp))
    }
}

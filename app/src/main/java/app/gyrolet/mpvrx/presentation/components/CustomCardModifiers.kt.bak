package app.gyrolet.mpvrx.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun getDynamicCardModifier(styleIndex: Int, opacity: Float, isFolder: Boolean): Modifier {
    val baseModifier = Modifier
    return when (styleIndex) {
        1 -> baseModifier.clip(RoundedCornerShape(16.dp)).background(Color.DarkGray.copy(alpha = opacity)).border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
        2 -> baseModifier.clip(RoundedCornerShape(24.dp)).background(Brush.linearGradient(listOf(Color(0xFF6200EE).copy(alpha = opacity), Color(0xFF03DAC5).copy(alpha = opacity))))
        3 -> baseModifier.clip(RoundedCornerShape(8.dp)).background(Color.Black.copy(alpha = opacity)).border(2.dp, Color(0xFF03DAC5), RoundedCornerShape(8.dp))
        4 -> baseModifier.clip(RoundedCornerShape(32.dp)).background(Brush.horizontalGradient(listOf(Color(0xFFFF5722).copy(alpha = opacity), Color(0xFFFFEB3B).copy(alpha = opacity))))
        5 -> baseModifier.clip(RoundedCornerShape(12.dp)).background(Color.Blue.copy(alpha = opacity * 0.7f)).border(1.dp, Color.White.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
        6 -> baseModifier.clip(RoundedCornerShape(20.dp)).background(Brush.verticalGradient(listOf(Color(0xFFE91E63).copy(alpha = opacity), Color(0xFF9C27B0).copy(alpha = opacity))))
        7 -> baseModifier.clip(RoundedCornerShape(0.dp)).background(Color.DarkGray.copy(alpha = opacity)).border(3.dp, Color.Red, RoundedCornerShape(0.dp))
        8 -> baseModifier.clip(RoundedCornerShape(28.dp)).background(Brush.radialGradient(listOf(Color(0xFF00BCD4).copy(alpha = opacity), Color(0xFF3F51B5).copy(alpha = opacity))))
        9 -> baseModifier.clip(RoundedCornerShape(16.dp)).background(Color.Magenta.copy(alpha = opacity * 0.5f)).border(1.dp, Color.Magenta, RoundedCornerShape(16.dp))
        10 -> baseModifier.clip(RoundedCornerShape(18.dp)).background(Brush.linearGradient(listOf(Color(0xFF4CAF50).copy(alpha = opacity), Color(0xFF8BC34A).copy(alpha = opacity))))
        11 -> baseModifier.clip(RoundedCornerShape(10.dp)).background(Color.DarkGray.copy(alpha = opacity)).border(2.dp, Color.Yellow, RoundedCornerShape(10.dp))
        12 -> baseModifier.clip(RoundedCornerShape(22.dp)).background(Brush.horizontalGradient(listOf(Color(0xFF9C27B0).copy(alpha = opacity), Color(0xFFE91E63).copy(alpha = opacity))))
        13 -> baseModifier.clip(RoundedCornerShape(14.dp)).background(Color.Cyan.copy(alpha = opacity * 0.4f)).border(1.dp, Color.Cyan, RoundedCornerShape(14.dp))
        14 -> baseModifier.clip(RoundedCornerShape(26.dp)).background(Brush.verticalGradient(listOf(Color(0xFFFF9800).copy(alpha = opacity), Color(0xFFFF5722).copy(alpha = opacity))))
        15 -> baseModifier.clip(RoundedCornerShape(6.dp)).background(Color.Black.copy(alpha = opacity)).border(2.dp, Color.Green, RoundedCornerShape(6.dp))
        16 -> baseModifier.clip(RoundedCornerShape(30.dp)).background(Brush.linearGradient(listOf(Color(0xFF3F51B5).copy(alpha = opacity), Color(0xFF2196F3).copy(alpha = opacity))))
        17 -> baseModifier.clip(RoundedCornerShape(15.dp)).background(Color.DarkGray.copy(alpha = opacity)).border(1.5.dp, Color.Cyan, RoundedCornerShape(15.dp))
        18 -> baseModifier.clip(RoundedCornerShape(12.dp)).background(Brush.radialGradient(listOf(Color(0xFFE91E63).copy(alpha = opacity), Color(0xFF3F51B5).copy(alpha = opacity))))
        19 -> baseModifier.clip(RoundedCornerShape(20.dp)).background(Color.Gray.copy(alpha = opacity)).border(2.dp, Color.White, RoundedCornerShape(20.dp))
        else -> baseModifier.clip(RoundedCornerShape(16.dp)).background(Brush.linearGradient(listOf(Color(0xFF1E1E1E).copy(alpha = opacity), Color(0xFF2D2D2D).copy(alpha = opacity)))).border(1.dp, Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
    }
}

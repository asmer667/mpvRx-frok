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
    val darkBase = Color(0xFF1A1A2E).copy(alpha = opacity)
    val darkerBase = Color(0xFF12121F).copy(alpha = opacity)

    return when (styleIndex) {
        // 1 - برتقالي → بنفسجي (قوي وحدود ناعمة)
        1 -> baseModifier
            .clip(RoundedCornerShape(18.dp))
            .background(darkBase)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFF6B35), Color(0xFF9B5DE5))
                ),
                shape = RoundedCornerShape(18.dp)
            )

        // 2 - سيان نيون أنيق
        2 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(darkerBase)
            .border(
                width = 1.8.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF00F5D4), Color(0xFF00BBF9))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 3 - رمادي داكن مع حدود زرقاء ناعمة
        3 -> baseModifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF1E1E2E).copy(alpha = opacity))
            .border(1.5.dp, Color(0xFF4CC9F0).copy(alpha = 0.7f), RoundedCornerShape(14.dp))

        // 4 - برتقالي → وردي (هندسي)
        4 -> baseModifier
            .clip(RoundedCornerShape(20.dp))
            .background(darkBase)
            .border(
                width = 2.2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFF9F1C), Color(0xFFE71D36))
                ),
                shape = RoundedCornerShape(20.dp)
            )

        // 5 - أخضر زمردي ناعم
        5 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F2027).copy(alpha = opacity))
            .border(
                width = 1.8.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF2EC4B6), Color(0xFF00A896))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 6 - ذهبي → برتقالي مع توهج خفيف
        6 -> baseModifier
            .clip(RoundedCornerShape(22.dp))
            .background(darkBase)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFFD166), Color(0xFFFF9F1C))
                ),
                shape = RoundedCornerShape(22.dp)
            )

        // 7 - أزرق عميق
        7 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0D1B2A).copy(alpha = opacity))
            .border(
                width = 1.8.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF00B4D8), Color(0xFF0077B6))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 8 - أحمر → بنفسجي (قوي)
        8 -> baseModifier
            .clip(RoundedCornerShape(18.dp))
            .background(darkBase)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFE63946), Color(0xFF9B5DE5))
                ),
                shape = RoundedCornerShape(18.dp)
            )

        // 9 - بنفسجي داكن مع حدود ذهبية خفيفة
        9 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1A0B2E).copy(alpha = opacity))
            .border(1.5.dp, Color(0xFFC77DFF).copy(alpha = 0.6f), RoundedCornerShape(16.dp))

        // 10 - أخضر ليموني → أخضر غامق
        10 -> baseModifier
            .clip(RoundedCornerShape(18.dp))
            .background(darkBase)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF80ED99), Color(0xFF38A3A5))
                ),
                shape = RoundedCornerShape(18.dp)
            )

        // 11 - بنفسجي هندسي
        11 -> baseModifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF240046).copy(alpha = opacity))
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF9B5DE5), Color(0xFFF15BB5))
                ),
                shape = RoundedCornerShape(14.dp)
            )

        // 12 - أزرق سماوي مع حدود نيون
        12 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(darkerBase)
            .border(
                width = 1.8.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF00F5D4), Color(0xFF00BBF9))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 13 - وردي ناعم مع قلب (خفيف)
        13 -> baseModifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF2B1B2E).copy(alpha = opacity))
            .border(
                width = 1.6.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFF85A1), Color(0xFFFF5D8F))
                ),
                shape = RoundedCornerShape(20.dp)
            )

        // 14 - سماوي → أزرق مع تأثير حركة
        14 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(darkBase)
            .border(
                width = 1.8.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF48CAE4), Color(0xFF0077B6))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 15 - برتقالي → وردي ساخن
        15 -> baseModifier
            .clip(RoundedCornerShape(18.dp))
            .background(darkBase)
            .border(
                width = 2.2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFF9F1C), Color(0xFFFF006E))
                ),
                shape = RoundedCornerShape(18.dp)
            )

        // 16 - أخضر داكن مع لمسة نيون
        16 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0B1320).copy(alpha = opacity))
            .border(
                width = 1.7.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF57CC99), Color(0xFF38A3A5))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 17 - أزرق نيون كلاسيك
        17 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(darkerBase)
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF00B4D8), Color(0xFF0077B6))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // 18 - ذهبي فاخر
        18 -> baseModifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF1A120B).copy(alpha = opacity))
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFFD166), Color(0xFFFF9F1C))
                ),
                shape = RoundedCornerShape(18.dp)
            )

        // 19 - وردي → بنفسجي (موسيقي)
        19 -> baseModifier
            .clip(RoundedCornerShape(20.dp))
            .background(darkBase)
            .border(
                width = 1.8.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFF85A1), Color(0xFF9B5DE5))
                ),
                shape = RoundedCornerShape(20.dp)
            )

        // 20 - أزرق داكن أنيق مع نقاط
        20 -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0D1B2A).copy(alpha = opacity))
            .border(
                width = 1.5.dp,
                brush = Brush.linearGradient(
                    listOf(Color(0xFF48CAE4), Color(0xFF023E8A))
                ),
                shape = RoundedCornerShape(16.dp)
            )

        // الافتراضي
        else -> baseModifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1E1E2E).copy(alpha = opacity))
            .border(1.dp, Color.LightGray.copy(alpha = 0.25f), RoundedCornerShape(16.dp))
    }
}
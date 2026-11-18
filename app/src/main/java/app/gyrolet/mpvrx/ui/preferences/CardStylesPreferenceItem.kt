package app.gyrolet.mpvrx.ui.preferences

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

@Composable
fun CardStylesPreferenceItem(onClick: () -> Unit) {
    PreferenceCard {
        // استخدام عنصر تفضيلات قياسي أو نص تفاعلي لتجنب أي نقص في معاملات البناء
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .androidx.compose.foundation.layout.padding(16.dp)
        ) {
            Text(text = "🎨 تخصيص أشكال وشفافية البطاقات (20 تصميماً)")
        }
    }
}

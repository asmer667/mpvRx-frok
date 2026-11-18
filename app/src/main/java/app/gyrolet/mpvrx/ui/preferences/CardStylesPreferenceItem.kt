package app.gyrolet.mpvrx.ui.preferences

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardStylesPreferenceItem(onClick: () -> Unit) {
    PreferenceCard {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp)
        ) {
            Text(text = "🎨 تخصيص أشكال وشفافية البطاقات (20 تصميماً)")
        }
    }
}

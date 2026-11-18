package app.gyrolet.mpvrx.ui.preferences

import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@Composable
fun CardStylesPreferenceCard(onClick: () -> Unit) {
    PreferenceCard {
        ExpressivePreferenceItem(
            title = "تخصيص أشكال وشفافية البطاقات",
            subtitle = "التحكم في الـ 20 تصميماً والشفافية للمجلدات والفيديوهات",
            onClick = onClick
        )
    }
}

package app.gyrolet.mpvrx.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ApplyDynamicCardContainer(
    styleIndex: Int,
    opacity: Float,
    isFolder: Boolean,
    content: @Composable () -> Unit
) {
    val dynamicModifier = getDynamicCardModifier(
        styleIndex = styleIndex,
        opacity = opacity,
        isFolder = isFolder
    )

    Box(
        modifier = dynamicModifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        content()
    }
}

package app.gyrolet.mpvrx.presentation.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.gyrolet.mpvrx.preferences.FolderAndVideoCardPreferences

@Composable
fun rememberDynamicCardModifier(isFolder: Boolean): Modifier {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("mpvrx_preferences", Context.MODE_PRIVATE)
    
    val styleIndex = if (isFolder) {
        FolderAndVideoCardPreferences.getFolderStyle(prefs)
    } else {
        FolderAndVideoCardPreferences.getVideoStyle(prefs)
    }
    
    val opacity = if (isFolder) {
        FolderAndVideoCardPreferences.getFolderOpacity(prefs)
    } else {
        FolderAndVideoCardPreferences.getVideoOpacity(prefs)
    }

    return getDynamicCardModifier(
        styleIndex = styleIndex,
        opacity = opacity,
        isFolder = isFolder
    )
}

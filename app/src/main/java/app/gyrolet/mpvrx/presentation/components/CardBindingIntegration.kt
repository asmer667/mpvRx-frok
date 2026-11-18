package app.gyrolet.mpvrx.presentation.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.gyrolet.mpvrx.preferences.FolderAndVideoCardPreferences

@Composable
fun getAppliedCardModifier(isFolder: Boolean): Modifier {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("mpvrx_preferences", Context.MODE_PRIVATE)

    val styleIndex = if (isFolder) {
        FolderAndVideoCardPreferences.getFolderStyle(sharedPrefs)
    } else {
        FolderAndVideoCardPreferences.getVideoStyle(sharedPrefs)
    }

    val opacity = if (isFolder) {
        FolderAndVideoCardPreferences.getFolderOpacity(sharedPrefs)
    } else {
        FolderAndVideoCardPreferences.getVideoOpacity(sharedPrefs)
    }

    return getDynamicCardModifier(
        styleIndex = styleIndex,
        opacity = opacity,
        isFolder = isFolder
    )
}

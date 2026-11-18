package app.gyrolet.mpvrx.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.gyrolet.mpvrx.preferences.FolderAndVideoCardPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderCardStylesScreen(isFolderMode: Boolean, onBackClick: () -> Unit) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("mpvrx_preferences", Context.MODE_PRIVATE)

    var selectedStyle by remember {
        mutableStateOf(
            if (isFolderMode) FolderAndVideoCardPreferences.getFolderStyle(prefs)
            else FolderAndVideoCardPreferences.getVideoStyle(prefs)
        )
    }

    var opacity by remember {
        mutableStateOf(
            if (isFolderMode) FolderAndVideoCardPreferences.getFolderOpacity(prefs)
            else FolderAndVideoCardPreferences.getVideoOpacity(prefs)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isFolderMode) "تخصيص بطاقات المجلدات" else "تخصيص بطاقات الفيديو") },
                navigationIcon = {
                    TextButton(onClick = onBackClick) { Text("رجوع", color = MaterialTheme.colorScheme.primary) }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            Text("اختر التصميم (من 1 إلى 20):", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(20) { index ->
                    val styleId = index + 1
                    val isSelected = selectedStyle == styleId
                    val cardModifier = getDynamicCardModifier(styleId, opacity, isFolderMode)

                    Box(
                        modifier = cardModifier
                            .height(70.dp)
                            .clickable {
                                selectedStyle = styleId
                                if (isFolderMode) {
                                    FolderAndVideoCardPreferences.setFolderStyle(prefs, styleId)
                                } else {
                                    FolderAndVideoCardPreferences.setVideoStyle(prefs, styleId)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$styleId",
                            color = if (isSelected) Color.Yellow else Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("درجة الشفافية: ${(opacity * 100).toInt()}%", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = opacity,
                onValueChange = { newOpacity ->
                    opacity = newOpacity
                    if (isFolderMode) {
                        FolderAndVideoCardPreferences.setFolderOpacity(prefs, newOpacity)
                    } else {
                        FolderAndVideoCardPreferences.setVideoOpacity(prefs, newOpacity)
                    }
                },
                valueRange = 0.2f..1f
            )
        }
    }
}

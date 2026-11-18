package app.gyrolet.mpvrx.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.gyrolet.mpvrx.preferences.FolderAndVideoCardPreferences
import app.gyrolet.mpvrx.ui.utils.popSafely

@kotlinx.serialization.Serializable
data object FolderCardStylesScreen : app.gyrolet.mpvrx.presentation.Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
    val backStack = app.gyrolet.mpvrx.ui.utils.LocalBackStack.current
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("mpvrx_preferences", Context.MODE_PRIVATE)

    var folderStyle by remember { mutableStateOf(FolderAndVideoCardPreferences.getFolderStyle(prefs)) }
    var videoStyle by remember { mutableStateOf(FolderAndVideoCardPreferences.getVideoStyle(prefs)) }
    var folderOpacity by remember { mutableStateOf(FolderAndVideoCardPreferences.getFolderOpacity(prefs)) }
    var videoOpacity by remember { mutableStateOf(FolderAndVideoCardPreferences.getVideoOpacity(prefs)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("تخصيص أشكال وشفافية البطاقات") },
                navigationIcon = {
                    TextButton(onClick = { backStack.popSafely() }) {
                        Text("الرجوع", color = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("شفافية بطاقات المجلدات: ${(folderOpacity * 100).toInt()}%", style = MaterialTheme.typography.titleMedium)
                Slider(
                    value = folderOpacity,
                    onValueChange = {
                        folderOpacity = it
                        FolderAndVideoCardPreferences.setFolderOpacity(prefs, it)
                    },
                    valueRange = 0.1f..1.0f
                )
            }

            item {
                Text("تصميم بطاقات المجلدات (اختر من 1 إلى 20): $folderStyle", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (folderStyle > 1) folderStyle--
                        else folderStyle = 20
                        FolderAndVideoCardPreferences.setFolderStyle(prefs, folderStyle)
                    }) {
                        Text("السابق")
                    }
                    Text("التصميم الحالي: $folderStyle", modifier = Modifier.align(Alignment.CenterVertically))
                    Button(onClick = {
                        if (folderStyle < 20) folderStyle++
                        else folderStyle = 1
                        FolderAndVideoCardPreferences.setFolderStyle(prefs, folderStyle)
                    }) {
                        Text("التالي")
                    }
                }
            }

            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }

            item {
                Text("شفافية بطاقات الفيديوهات: ${(videoOpacity * 100).toInt()}%", style = MaterialTheme.typography.titleMedium)
                Slider(
                    value = videoOpacity,
                    onValueChange = {
                        videoOpacity = it
                        FolderAndVideoCardPreferences.setVideoOpacity(prefs, it)
                    },
                    valueRange = 0.1f..1.0f
                )
            }

            item {
                Text("تصميم بطاقات الفيديوهات (اختر من 1 إلى 20): $videoStyle", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (videoStyle > 1) videoStyle--
                        else videoStyle = 20
                        FolderAndVideoCardPreferences.setVideoStyle(prefs, videoStyle)
                    }) {
                        Text("السابق")
                    }
                    Text("التصميم الحالي: $videoStyle", modifier = Modifier.align(Alignment.CenterVertically))
                    Button(onClick = {
                        if (videoStyle < 20) videoStyle++
                        else videoStyle = 1
                        FolderAndVideoCardPreferences.setVideoStyle(prefs, videoStyle)
                    }) {
                        Text("التالي")
                    }
                }
            }
        }
    }
}


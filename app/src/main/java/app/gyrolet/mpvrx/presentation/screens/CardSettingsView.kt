package app.gyrolet.mpvrx.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.gyrolet.mpvrx.preferences.FolderAndVideoCardPreferences

@Composable
fun CardCustomizationSettingsSection() {
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences("mpvrx_preferences", Context.MODE_PRIVATE)
    }

    var folderStyle by remember { mutableStateOf(FolderAndVideoCardPreferences.getFolderStyle(sharedPrefs)) }
    var folderOpacity by remember { mutableStateOf(FolderAndVideoCardPreferences.getFolderOpacity(sharedPrefs)) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "تخصيص أشكال المجلدات والبطاقات (20 شكل)",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        // اختيار رقم الشكل (من 1 إلى 20)
        Text(text = "رقم الشكل الحالي: $folderStyle")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if (folderStyle > 1) {
                    folderStyle--
                    FolderAndVideoCardPreferences.setFolderStyle(sharedPrefs, folderStyle)
                }
            }) {
                Text("السابق")
            }
            
            Button(onClick = {
                if (folderStyle < 20) {
                    folderStyle++
                    FolderAndVideoCardPreferences.setFolderStyle(sharedPrefs, folderStyle)
                }
            }) {
                Text("التالي")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // شريط التحكم بالشفافية
        Text(text = "درجة الشفافية: ${String.format("%.2f", folderOpacity)}")
        Slider(
            value = folderOpacity,
            onValueChange = { newValue ->
                folderOpacity = newValue
                FolderAndVideoCardPreferences.setFolderOpacity(sharedPrefs, newValue)
            },
            valueRange = 0.1f..1.0f
        )
    }
}

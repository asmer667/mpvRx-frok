package app.gyrolet.mpvrx.preferences

import android.content.SharedPreferences

object FolderAndVideoCardPreferences {
    private const val KEY_FOLDER_STYLE = "key_folder_card_style"
    private const val KEY_VIDEO_STYLE = "key_video_card_style"
    private const val KEY_FOLDER_OPACITY = "key_folder_card_opacity"
    private const val KEY_VIDEO_OPACITY = "key_video_card_opacity"

    fun getFolderStyle(prefs: SharedPreferences): Int = prefs.getInt(KEY_FOLDER_STYLE, 1)
    fun setFolderStyle(prefs: SharedPreferences, style: Int) = prefs.edit().putInt(KEY_FOLDER_STYLE, style).apply()

    fun getVideoStyle(prefs: SharedPreferences): Int = prefs.getInt(KEY_VIDEO_STYLE, 1)
    fun setVideoStyle(prefs: SharedPreferences, style: Int) = prefs.edit().putInt(KEY_VIDEO_STYLE, style).apply()

    fun getFolderOpacity(prefs: SharedPreferences): Float = prefs.getFloat(KEY_FOLDER_OPACITY, 0.85f)
    fun setFolderOpacity(prefs: SharedPreferences, opacity: Float) = prefs.edit().putFloat(KEY_FOLDER_OPACITY, opacity).apply()

    fun getVideoOpacity(prefs: SharedPreferences): Float = prefs.getFloat(KEY_VIDEO_OPACITY, 0.85f)
    fun setVideoOpacity(prefs: SharedPreferences, opacity: Float) = prefs.edit().putFloat(KEY_VIDEO_OPACITY, opacity).apply()
}

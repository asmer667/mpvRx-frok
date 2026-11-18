package app.gyrolet.mpvrx.preferences

import android.content.SharedPreferences

object FolderAndVideoCardPreferences {
    private const val KEY_FOLDER_STYLE = "folder_card_style_custom_key"
    private const val KEY_FOLDER_OPACITY = "folder_card_opacity_custom_key"
    private const val KEY_VIDEO_STYLE = "video_card_style_custom_key"
    private const val KEY_VIDEO_OPACITY = "video_card_opacity_custom_key"

    fun getFolderStyle(prefs: SharedPreferences): Int = prefs.getInt(KEY_FOLDER_STYLE, 1)
    fun setFolderStyle(prefs: SharedPreferences, value: Int) = prefs.edit().putInt(KEY_FOLDER_STYLE, value).apply()

    fun getFolderOpacity(prefs: SharedPreferences): Float = prefs.getFloat(KEY_FOLDER_OPACITY, 0.85f)
    fun setFolderOpacity(prefs: SharedPreferences, value: Float) = prefs.edit().putFloat(KEY_FOLDER_OPACITY, value).apply()

    fun getVideoStyle(prefs: SharedPreferences): Int = prefs.getInt(KEY_VIDEO_STYLE, 1)
    fun setVideoStyle(prefs: SharedPreferences, value: Int) = prefs.edit().putInt(KEY_VIDEO_STYLE, value).apply()

    fun getVideoOpacity(prefs: SharedPreferences): Float = prefs.getFloat(KEY_VIDEO_OPACITY, 0.85f)
    fun setVideoOpacity(prefs: SharedPreferences, value: Float) = prefs.edit().putFloat(KEY_VIDEO_OPACITY, value).apply()
}

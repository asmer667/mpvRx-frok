package app.gyrolet.mpvrx.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

object CardLayoutStyleRuntime {
  var folderId by mutableIntStateOf(0)
    private set
  var videoId by mutableIntStateOf(0)
    private set
  var episodeId by mutableIntStateOf(0)
    private set

  fun randomize(folder: Boolean, video: Boolean, episode: Boolean) {
    if (folder) folderId = Random.nextInt(0, 20)
    if (video) videoId = Random.nextInt(0, 20)
    if (episode) episodeId = Random.nextInt(0, 20)
  }
}

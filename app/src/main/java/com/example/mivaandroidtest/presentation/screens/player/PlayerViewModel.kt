package com.example.mivaandroidtest.presentation.screens.player

import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.example.mivaandroidtest.data.datasources.cache.Bookmark
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @OptIn(UnstableApi::class)
@Inject constructor(
    val videoCacheManager: VideoCacheManager,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _currentVideo = MutableStateFlow<String?>(null)
    val currentVideo: StateFlow<String?> = _currentVideo

    private var videoList: List<String> = emptyList()

    private val _bookmarks = MutableStateFlow<List<Bookmark>>(emptyList())
    val bookmarks: StateFlow<List<Bookmark>> = _bookmarks


    fun setVideoList(videos: List<String>) {
        videoList = videos
        if (_currentVideo.value == null && videoList.isNotEmpty()) {
            setCurrentVideo(videoList.first())
        }
        cacheCurrentAndAdjacentVideos()
    }

    private fun setCurrentVideo(videoUrl: String) {
        _currentVideo.value = videoUrl
        cacheCurrentAndAdjacentVideos()
    }

    private fun cacheCurrentAndAdjacentVideos() {
        val currentIndex = videoList.indexOf(_currentVideo.value)
        if (currentIndex == -1) return

        val toCache = listOfNotNull(
            videoList.getOrNull(currentIndex - 1),
            videoList.getOrNull(currentIndex),
            videoList.getOrNull(currentIndex + 1)
        )

        toCache.forEach { url ->
            if (url == _currentVideo.value || isConnectedToWifi()) {
                cacheVideo(url)
            }
        }
    }

    @OptIn(UnstableApi::class)
    private fun cacheVideo(videoUrl: String) {
        val cacheDataSourceFactory = videoCacheManager.getCacheDataSourceFactory()
        val mediaSource = ProgressiveMediaSource.Factory(cacheDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoUrl))

        val player = ExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(cacheDataSourceFactory))
            .build()

        player.setMediaSource(mediaSource)
        player.prepare()
        player.release()
    }

    private fun isConnectedToWifi(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.type == ConnectivityManager.TYPE_WIFI
    }

    fun addBookmark(timestamp: Long, note: String) {
        val updatedBookmarks = _bookmarks.value.toMutableList().apply {
            add(Bookmark(timestamp, note))
        }
        _bookmarks.value = updatedBookmarks
    }
}

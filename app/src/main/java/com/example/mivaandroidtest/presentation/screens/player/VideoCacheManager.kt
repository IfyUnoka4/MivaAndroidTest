package com.example.mivaandroidtest.presentation.screens.player

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File

@UnstableApi
class VideoCacheManager(context: Context) {
    private val cacheSize: Long = 100 * 1024 * 1024
    private val cacheDir = File(context.cacheDir, "video_cache")
    private val databaseProvider = StandaloneDatabaseProvider(context)
    private val evictor = LeastRecentlyUsedCacheEvictor(cacheSize)

    val simpleCache: SimpleCache = SimpleCache(cacheDir, evictor, databaseProvider)


    fun getCacheDataSourceFactory(): CacheDataSource.Factory {
        return CacheDataSource.Factory()
            .setCache(simpleCache)
            .setUpstreamDataSourceFactory(DefaultHttpDataSource.Factory())
            .setCacheWriteDataSinkFactory(null)
    }

    fun releaseCache() {
        simpleCache.release()
    }
}
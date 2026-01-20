package com.example.instgramtask.Model.Cache

import android.content.Context
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource

object VideoDataSourceFactory {

    fun create(context: Context): DataSource.Factory {

        val upstreamFactory = DefaultDataSource.Factory(context)

        return CacheDataSource.Factory()
            .setCache(VideoCache.getCache(context))
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }
}

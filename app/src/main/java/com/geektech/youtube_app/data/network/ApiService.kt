package com.geektech.youtube_app.data.network


import com.geektech.youtube_app.BuildConfig.API_KEY
import com.geektech.youtube_app.domain.model.playlist.PlayList
import com.geektech.youtube_app.domain.model.playlistIem.PlaylistVideos
import com.geektech.youtube_app.utils.CHANNEL_ID
import com.geektech.youtube_app.utils.MAX_RESULT
import com.geektech.youtube_app.utils.PART
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String = PART,
        @Query("channelId") channelId: String = CHANNEL_ID,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = MAX_RESULT
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") partItems: String = PART,
        @Query("playlistId") playlistId: String?,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = MAX_RESULT
    ): Response<PlaylistVideos>
}
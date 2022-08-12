package com.geektech.youtube_app.domain.model.playlistIem


data class PlaylistVideos(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)
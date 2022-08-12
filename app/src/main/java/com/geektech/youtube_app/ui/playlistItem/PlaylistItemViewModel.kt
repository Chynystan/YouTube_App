package com.geektech.youtube_app.ui.playlistItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geektech.youtube_app.data.repo.PlaylistRepository
import com.geektech.youtube_app.domain.common.Resource
import com.geektech.youtube_app.domain.model.playlistIem.PlaylistVideos

class PlaylistItemViewModel(private val repo: PlaylistRepository) : ViewModel() {
    var liveData: LiveData<Resource<PlaylistVideos?>> = MutableLiveData()

    fun getPlaylistVideo(id: String) {
        liveData = repo.getPlaylistItem(id)
    }
}
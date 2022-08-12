package com.geektech.youtube_app.ui.playlist

import androidx.lifecycle.ViewModel
import com.geektech.youtube_app.data.repo.PlaylistRepository

class PlaylistViewModel(repo: PlaylistRepository) : ViewModel() {
    val liveData = repo.getPlaylists()
}
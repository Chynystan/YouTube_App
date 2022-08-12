package com.geektech.youtube_app.di

import com.geektech.youtube_app.ui.playlist.PlaylistViewModel
import com.geektech.youtube_app.ui.playlistItem.PlaylistItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { PlaylistItemViewModel(get()) }
}
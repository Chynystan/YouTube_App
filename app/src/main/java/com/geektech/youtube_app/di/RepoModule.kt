package com.geektech.youtube_app.di

import com.geektech.youtube_app.data.repo.PlaylistRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModule: Module = module {
    factory { PlaylistRepository(get()) }


}
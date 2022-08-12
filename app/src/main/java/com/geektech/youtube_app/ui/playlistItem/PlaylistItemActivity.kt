package com.geektech.youtube_app.ui.playlistItem

import android.content.Intent
import android.os.Bundle
import com.geektech.youtube_app.R

import com.geektech.youtube_app.base.BaseActivity
import com.geektech.youtube_app.databinding.ActivityPlaylistItemBinding

import com.geektech.youtube_app.domain.common.Resource
import com.geektech.youtube_app.ui.playlistItem.adapter.PlaylistItemAdapter
import com.geektech.youtube_app.utils.gone
import com.geektech.youtube_app.utils.isOnline
import com.geektech.youtube_app.utils.showToast
import com.geektech.youtube_app.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistItemActivity :
    BaseActivity<ActivityPlaylistItemBinding>(ActivityPlaylistItemBinding::inflate){
    private val viewModelItem: PlaylistItemViewModel by viewModel()
    private lateinit var adapter: PlaylistItemAdapter

    override fun chekInternet() {
        if (isOnline()) {
            setupUI()
            setupObserver()
        } else {
            showToast("No Internet connection")
            binding.checkInternet.root.visible()
        }
    }

    private fun getData() {
        val extras: Bundle? = intent.extras
        val id = extras?.getString("playlistId")
        id?.let { viewModelItem.getPlaylistVideo(it) }

    }


    private fun setupUI() {
        binding.txtMainTitle.text = getString(R.string.title)
        adapter = PlaylistItemAdapter()
        binding.rvPlayer.adapter = adapter
        getData()
    }

    private fun setupObserver() {
        viewModelItem.liveData.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.items?.let { it1 -> adapter.setPlaylist(it1) }
                    binding.progress.gone()
                }
                Resource.Status.LOADING -> binding.progress.visible()
                Resource.Status.ERROR -> {
                    binding.progress.gone()
                    showToast(it.message + " ")
                }
            }
        }
    }
}

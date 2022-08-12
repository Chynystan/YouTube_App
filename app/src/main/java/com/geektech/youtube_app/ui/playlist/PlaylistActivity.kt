package com.geektech.youtube_app.ui.playlist

import android.content.Intent
import com.geektech.youtube_app.base.BaseActivity
import com.geektech.youtube_app.databinding.ActivityMainBinding

import com.geektech.youtube_app.domain.common.Resource
import com.geektech.youtube_app.ui.playlist.adapter.PlaylistAdapter
import com.geektech.youtube_app.ui.playlistItem.PlaylistItemActivity
import com.geektech.youtube_app.utils.gone
import com.geektech.youtube_app.utils.isOnline
import com.geektech.youtube_app.utils.showToast
import com.geektech.youtube_app.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    PlaylistAdapter.SendKeyPlayList<String> {
    private lateinit var adapter: PlaylistAdapter
    private val playlistViewModel: PlaylistViewModel by viewModel()
    override fun initListener() {
        super.initListener()
        binding.checkInternet.btnCheckInternet.setOnClickListener {
            chekInternet()
        }
    }

    override fun chekInternet() {
        if (isOnline()) {
            binding.checkInternet.root.gone()
            setupUI()
            setupObserver()
        } else {
            showToast("No Internet connection")
            binding.checkInternet.root.visible()
        }
    }

    private fun setupUI() {
        adapter = PlaylistAdapter(this)
        binding.rvPlaylist.adapter = adapter
    }

    private fun setupObserver() {
        playlistViewModel.liveData.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progress.gone()
                    it.data?.items?.let { it1 -> adapter.setPlaylist(it1) }
                }
                Resource.Status.LOADING -> binding.progress.visible()
                Resource.Status.ERROR -> showToast(it.message + "efeef")
            }
        }
    }

    override fun onItemClick(data: String) {
        val intent = Intent(this, PlaylistItemActivity::class.java)
        intent.putExtra("playlistId", data)
        startActivity(intent)
    }
}
package com.example.joe.lastfmchallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.common.AppInjector
import com.example.joe.lastfmchallenge.data.models.album.Album
import com.example.joe.lastfmchallenge.di.factories.AlbumDetailViewModelFactory
import com.example.joe.lastfmchallenge.di.fragment.FragmentComponent
import com.example.joe.lastfmchallenge.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.fragment_album_detail.*
import javax.inject.Inject

class AlbumDetailFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: AlbumDetailViewModelFactory
    @Inject
    lateinit var fragmentComponent: FragmentComponent
    lateinit var viewModelAlbumAlbum: AlbumDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        viewModelAlbumAlbum = ViewModelProviders.of(this, viewModelFactory).get(AlbumDetailViewModel::class.java)
        viewModelAlbumAlbum.albumDetails.observe(this, Observer {
            it?.apply {
                tvNameAlbum.text = name
                tvAlbumArtistDetail.text = artist
                tvAlbumListenersDetail.text = listeners
                tvAlbumTracksDetails.text = tracks.track.size.toString()
            }
        })


        viewModelAlbumAlbum.progressLiveData.observe(this, Observer {
            resetContent()
            when (it) {
                ApiProgress.LOADING -> {
                    detailProgressBar.visibility = View.VISIBLE
                }
                ApiProgress.SUCCESS -> {
                    detail_layout.visibility = View.VISIBLE
                }

                ApiProgress.FAILURE -> {
                    tvMessage.visibility = View.VISIBLE
                }
            }
        })

        val album = arguments?.getParcelable<Album>("album")

        album?.apply {
            viewModelAlbumAlbum.getAlbumDetails(this.artist, this.name)
        }

    }

    private fun resetContent() {
        detail_layout.visibility = View.GONE
        detailProgressBar.visibility = View.GONE
        tvMessage.visibility = View.GONE
    }

    private fun injectDependencies() {
        fragmentComponent =
            (activity!!.application as AppInjector).applicationComponent.newActivityComponent(
                FragmentModule()
            )
        fragmentComponent.injectAlbumDetailFragment(this)
    }
}
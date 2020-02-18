package com.example.joe.lastfmchallenge.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.adapter.AlbumAdapter
import com.example.joe.lastfmchallenge.common.ALBUM
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.common.AppInjector
import com.example.joe.lastfmchallenge.common.TYPE
import com.example.joe.lastfmchallenge.di.factories.AlbumViewModelFactory
import com.example.joe.lastfmchallenge.di.fragment.FragmentComponent
import com.example.joe.lastfmchallenge.di.fragment.FragmentModule
import com.example.joe.lastfmchallenge.ui.detail.AlbumDetailFragment
import com.example.joe.lastfmchallenge.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_album.*
import javax.inject.Inject


class AlbumFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: AlbumViewModelFactory
    @Inject
    lateinit var fragmentComponent: FragmentComponent
    private lateinit var viewModel: AlbumViewModel
    private lateinit var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager
    private lateinit var albumAdapter: AlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)

        albumRecycler.layoutManager = linearLayoutManager
        albumAdapter = AlbumAdapter(mutableListOf()) {
            val bundle = Bundle()
            bundle.putParcelable(ALBUM, it)
            bundle.putString(TYPE, ALBUM)
            (activity as HomeActivity).displayFragment(AlbumDetailFragment().apply {
                arguments = bundle
            }, true)
        }
        albumRecycler.adapter = albumAdapter
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumViewModel::class.java)
        detail_layout.visibility = View.GONE

        viewModel.albumListLiveData.observe(this, Observer {
            it?.apply {
                albumAdapter.refreshData(it)
            }

        })

        viewModel.errorLiveData.observe(this, Observer {
            it?.apply {
                tvMessage.text = it
            }
        })

        viewModel.progressLiveData.observe(this, Observer {
            resetContent()
            when (it) {
                ApiProgress.SUCCESS -> {
                    albumRecycler.visibility = View.VISIBLE
                }
                ApiProgress.FAILURE -> {
                    tvMessage.visibility = View.VISIBLE
                }
                ApiProgress.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })

        imageButton.setOnClickListener {
            viewModel.getAlbum(albumSearch.text.toString())
        }
    }

    private fun resetContent() {
        albumRecycler.visibility = View.GONE
        progressBar.visibility = View.GONE
        tvMessage.visibility = View.GONE
    }

    private fun injectDependencies() {
        fragmentComponent =
            (activity!!.application as AppInjector).applicationComponent.newActivityComponent(
                FragmentModule()
            )
        fragmentComponent.injectAlbumFragment(this)
    }
}
package com.example.joe.lastfmchallenge.ui.artist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.adapter.ArtistAdapter
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.common.AppInjector
import com.example.joe.lastfmchallenge.di.factories.ArtistViewModelFatory
import com.example.joe.lastfmchallenge.di.fragment.FragmentComponent
import com.example.joe.lastfmchallenge.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.fragment_artist.*
import javax.inject.Inject

class ArtistFragment : Fragment() {
    @Inject lateinit var viewModelFactory: ArtistViewModelFatory
    @Inject lateinit var fragmentComponent: FragmentComponent
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var artistAdapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ArtistFragment", "Create")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_artist, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        linearLayoutManager = LinearLayoutManager(context)
        artistAdapter = ArtistAdapter(mutableListOf()) { artist->
            Toast.makeText(context, artist.listeners, Toast.LENGTH_SHORT).show()
        }
        artistRecycler.adapter = artistAdapter
        artistRecycler.layoutManager = linearLayoutManager
        artistViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArtistViewModel::class.java)

        artistViewModel.artistListLiveData.observe(this, Observer {
            it?.apply {
                artistAdapter.refreshData(it)

            }
        })

        artistViewModel.errorLiveData.observe(this, Observer {
            it?.apply {
                tvMessageArtist.text = it
            }
        })

        artistViewModel.progressLiveData.observe(this, Observer{
            it?.apply {
                refreshContent()
                when (it) {
                    ApiProgress.SUCCESS -> {
                        artistRecycler.visibility = View.VISIBLE
                    }
                    ApiProgress.FAILURE -> {
                        tvMessageArtist.visibility = View.VISIBLE
                    }
                    ApiProgress.LOADING -> {
                        artistProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

        imageButtonArtist.setOnClickListener{
            artistViewModel.getArtist(artistSearch.text.toString())
        }
    }


    private fun refreshContent(){
        tvMessageArtist.visibility = View.GONE
        artistRecycler.visibility = View.GONE
        artistProgressBar.visibility = View.GONE
    }
    private fun injectDependencies() {
        fragmentComponent =
            (activity!!.application as AppInjector).applicationComponent.newActivityComponent(
                FragmentModule()
            )
        fragmentComponent.injectArtistFragment(this)
    }

}

package com.example.joe.lastfmchallenge.ui.track

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
import com.example.joe.lastfmchallenge.adapter.TrackAdapter
import com.example.joe.lastfmchallenge.common.ApiProgress
import com.example.joe.lastfmchallenge.common.AppInjector
import com.example.joe.lastfmchallenge.di.factories.TrackViewModelFactory
import com.example.joe.lastfmchallenge.di.fragment.FragmentComponent
import com.example.joe.lastfmchallenge.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.fragment_track.*
import javax.inject.Inject

class TrackFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory: TrackViewModelFactory
    @Inject
    lateinit var fragmentComponent: FragmentComponent
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: TrackViewModel
    private lateinit var trackAdapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Track Fragment", "Created")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        linearLayoutManager = LinearLayoutManager(context)
        trackAdapter = TrackAdapter(mutableListOf()) { track ->
            Toast.makeText(context, track.listeners, Toast.LENGTH_SHORT).show()
        }
        rvTrack.adapter = trackAdapter
        rvTrack.layoutManager = linearLayoutManager
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrackViewModel::class.java)

        viewModel.trackListLiveData.observe(this, Observer {
            it?.apply {
                trackAdapter.refreshData(it)

            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            it?.apply {
                tvMessageTrack.text = it
            }
        })

        viewModel.progressLiveData.observe(this, Observer{
            it?.apply {
                refreshContent()
                when (it) {
                    ApiProgress.SUCCESS -> {
                        rvTrack.visibility = View.VISIBLE
                    }
                    ApiProgress.FAILURE -> {
                        tvMessageTrack.visibility = View.VISIBLE
                    }
                    ApiProgress.LOADING -> {
                        trackProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

        imageButtonTrack.setOnClickListener{
            viewModel.getTrack(trackSearch.text.toString())
        }
    }

    private fun refreshContent(){
        tvMessageTrack.visibility = View.GONE
        rvTrack.visibility = View.GONE
        trackProgressBar.visibility = View.GONE
    }
    private fun injectDependencies() {
        fragmentComponent =
            (activity!!.application as AppInjector).applicationComponent.newActivityComponent(
                FragmentModule()
            )
        fragmentComponent.injectTrackFragment(this)
    }
}
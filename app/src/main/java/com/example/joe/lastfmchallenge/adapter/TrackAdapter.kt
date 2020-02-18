package com.example.joe.lastfmchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.data.models.tracks.Track
import kotlinx.android.synthetic.main.view_holder_track.view.*

class TrackAdapter(private val trackList:MutableList<Track>, private val onTrackClick:(Track)->Unit): RecyclerView.Adapter<TrackAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_track, viewGroup, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = trackList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trackList[position], context, onTrackClick)
    }

    fun refreshData(tracks:List<Track>){
        trackList.clear()
        trackList.addAll(tracks)
        notifyDataSetChanged()
    }

    class ViewHolder(trackView: View):RecyclerView.ViewHolder(trackView) {
        fun bind(temp: Track, context: Context, onTrackClick: (Track) -> Unit) {
            itemView.tvTrackName.text = temp.name
            itemView.tvListeners.text = temp.listeners
            itemView.tvArtistName.text = temp.streamable
            Glide.with(context).load(temp.image[1].text).into(itemView.imageViewTrack)
            itemView.setOnClickListener {
                onTrackClick.invoke(temp)
            }
        }
    }

}


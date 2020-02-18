package com.example.joe.lastfmchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.data.models.tracks.Track

class TrackAdapter(private val trackList:MutableList<Track>, private val trackListener: TrackClickListener): RecyclerView.Adapter<TrackAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_track, viewGroup, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = trackList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = trackList[position]
        holder.name.text = temp.name
        holder.listeners.text = temp.listeners
        holder.artistName.text = temp.streamable
        Glide.with(context).load(temp.image[1].text).into(holder.image)
        holder.itemView.setOnClickListener {
            trackListener.onClick(temp)
        }
    }

    fun refreshData(tracks:List<Track>){
        trackList.clear()
        trackList.addAll(tracks)
        notifyDataSetChanged()
    }

    class ViewHolder(trackView: View):RecyclerView.ViewHolder(trackView) {
        val name : TextView = trackView.findViewById(R.id.tvTrackName)
        val listeners: TextView = trackView.findViewById(R.id.tvListeners)
        val artistName: TextView = trackView.findViewById(R.id.tvArtistName)
        val image: ImageView = trackView.findViewById(R.id.imageViewTrack)
    }

    interface TrackClickListener {
        fun onClick(track: Track)
    }
}


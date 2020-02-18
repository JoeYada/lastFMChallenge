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
import com.example.joe.lastfmchallenge.data.models.artists.Artist

class ArtistAdapter(private val artistList: MutableList<Artist>, private val artistClickListener: ArtistClickListener):RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_artist, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = artistList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = artistList[position]
        holder.name.text = temp.name
        holder.listeners.text = temp.listeners
        holder.streamable.text = temp.streamable
        Glide.with(context).load(temp.image[1].text).into(holder.image)
        holder.itemView.setOnClickListener {
            artistClickListener.onclick(temp)
        }
    }

    fun refreshData(artists:List<Artist>){
        artistList.clear()
        artistList.addAll(artists)
        notifyDataSetChanged()
    }

    class ViewHolder(artistView: View):RecyclerView.ViewHolder(artistView) {
        val name :TextView = artistView.findViewById(R.id.tvArtistName)
        val listeners: TextView = artistView.findViewById(R.id.tvListeners)
        val streamable: TextView = artistView.findViewById(R.id.tvStreamable)
        val image: ImageView = artistView.findViewById(R.id.imageViewArtist)
    }

    interface ArtistClickListener{
        fun onclick(artist: Artist)
    }
}
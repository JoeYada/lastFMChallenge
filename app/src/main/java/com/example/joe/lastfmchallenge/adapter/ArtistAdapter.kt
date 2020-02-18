package com.example.joe.lastfmchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.joe.lastfmchallenge.R
import com.example.joe.lastfmchallenge.data.models.artists.Artist
import kotlinx.android.synthetic.main.view_holder_artist.view.*
import kotlinx.android.synthetic.main.view_holder_track.view.tvArtistName
import kotlinx.android.synthetic.main.view_holder_track.view.tvListeners

class ArtistAdapter(private val artistList: MutableList<Artist>, private val onArtistClick:(Artist)->Unit):RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_artist, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = artistList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistList[position], context, onArtistClick)
    }

    fun refreshData(artists:List<Artist>){
        artistList.clear()
        artistList.addAll(artists)
        notifyDataSetChanged()
    }

    class ViewHolder(artistView: View):RecyclerView.ViewHolder(artistView) {
        fun bind(artist: Artist, context: Context, onArtistClick: (Artist) -> Unit) {
            itemView.tvArtistName.text = artist.name
            itemView.tvListeners.text = artist.listeners
            itemView.tvStreamable.text = artist.streamable
            Glide.with(context).load(artist.image[1].text).into(itemView.imageViewArtist)
            itemView.setOnClickListener {
                onArtistClick.invoke(artist)
            }
        }
    }

}
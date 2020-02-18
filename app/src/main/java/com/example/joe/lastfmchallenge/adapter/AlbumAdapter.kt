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
import com.example.joe.lastfmchallenge.data.models.album.Album
import kotlinx.android.synthetic.main.view_holder_album.view.*

class AlbumAdapter(
    private val albumList: MutableList<Album>,
    private val onAlbumClick: (Album)->Unit
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        context = viewGroup.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_album, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = albumList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumList[position], context, onAlbumClick)

    }

    class ViewHolder(albumView: View) : RecyclerView.ViewHolder(albumView) {
        fun bind(temp: Album, context: Context, onAlbumClick: (Album) -> Unit) {
            itemView.tvAlbumName.text = temp.name
            itemView.tvArtist.text = temp.artist
            Glide.with(context).load(temp.image[1].text).into(itemView.imageViewAlbum)
            itemView.setOnClickListener {
                onAlbumClick.invoke(temp)
            }
        }
    }


    fun refreshData(newItems: List<Album>) {
        albumList.clear()
        albumList.addAll(newItems)
        notifyDataSetChanged()
    }

}
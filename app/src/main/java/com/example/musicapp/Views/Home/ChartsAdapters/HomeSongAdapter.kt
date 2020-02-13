package com.example.musicapp.Views.Home.ChartsAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.Models.Track
import com.example.musicapp.R
import com.example.musicapp.Views.SongFragments
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_charts_layout.view.topChartsImg
import kotlinx.android.synthetic.main.top_charts_layout.view.topChartsNameTV
import kotlinx.android.synthetic.main.top_songs_charts_layout.view.*

class HomeSongAdapter(var context : Context, var tracks : List<Track>,var frag : SongFragments) :
    RecyclerView.Adapter<HomeSongAdapter.HomeSongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        HomeSongViewHolder(
            LayoutInflater.from(context).inflate(R.layout.top_songs_charts_layout, parent, false)
        )
    override fun getItemCount() = tracks.size
    override fun onBindViewHolder(holder: HomeSongViewHolder, position: Int) {
        holder.name.text = tracks[position].title
        Picasso.get().load(tracks[position].album.cover_big).into(holder.imageView)
        bindDuration(tracks[position].duration,holder.duration)
        holder.layout.setOnClickListener {
           frag.goToSongFragment(position)
        }
    }

    private fun bindDuration(duration: Int, textView: TextView) {
        var min = duration/60
        var sec = duration%60
        textView.text = "$min:$sec"
    }

    class HomeSongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.topChartsImg
        val name = view.topChartsNameTV
        val duration = view.songDuration
        val layout = view.songLayout
    }
}
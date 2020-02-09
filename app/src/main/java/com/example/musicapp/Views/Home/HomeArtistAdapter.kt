package com.example.musicapp.Views.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.Models.Artist
import com.example.musicapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_charts_layout.view.*

class HomeArtistAdapter(var context : Context,var artists: List<Artist>) :
    RecyclerView.Adapter<HomeArtistAdapter.HomeArtistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = HomeArtistViewHolder(
        LayoutInflater.from(context).inflate(R.layout.top_charts_layout, parent, false)
    )
    override fun getItemCount() = artists.size
    override fun onBindViewHolder(holder: HomeArtistViewHolder, position: Int) {
        holder.name.text = artists[position].name
        Picasso.get().load(artists[position].picture_big).into(holder.imageView)

    }
    class HomeArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val imageView = view.topChartsImg
         val name = view.topChartsNameTV
    }
}
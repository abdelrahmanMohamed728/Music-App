package com.example.musicapp.Models

import android.os.Parcel
import android.os.Parcelable

data class Album(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val id: Int,
    val link: String,
    val position: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(artist, 0)
        writeString(cover)
        writeString(cover_big)
        writeString(cover_medium)
        writeString(cover_small)
        writeString(cover_xl)
        writeInt((if (explicit_lyrics) 1 else 0))
        writeInt(id)
        writeString(link)
        writeInt(position)
        writeString(record_type)
        writeString(title)
        writeString(tracklist)
        writeString(type)
    }

}
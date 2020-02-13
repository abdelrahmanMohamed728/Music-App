package com.example.musicapp.Models

import android.os.Parcel
import android.os.Parcelable

data class Track(
    val album: Album,
    var artist: Artist,
    val duration: Int,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: Int,
    val link: String,
    val position: Int,
    val preview: String,
    val rank: Int,
    val title: String,
    val title_short: String,
    val title_version: String,
    val type: String
) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(album, 0)
        writeParcelable(artist, 0)
        writeInt(duration)
        writeInt(explicit_content_cover)
        writeInt(explicit_content_lyrics)
        writeInt((if (explicit_lyrics) 1 else 0))
        writeInt(id)
        writeString(link)
        writeInt(position)
        writeString(preview)
        writeInt(rank)
        writeString(title)
        writeString(title_short)
        writeString(title_version)
        writeString(type)
    }

}

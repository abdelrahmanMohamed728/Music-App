package com.example.musicapp.Models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Artist (
    val id: Int,
    val link: String,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val radio: Boolean,
    val tracklist: String,
    val type: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
      dest?.writeInt(id)
      dest?.writeString(link)
      dest?.writeString(name)
      dest?.writeString(picture)
      dest?.writeString(picture_small)
      dest?.writeString(picture_medium)
      dest?.writeString(picture_big)
      dest?.writeString(picture_xl)
      dest?.writeBoolean(radio)
      dest?.writeString(tracklist)
      dest?.writeString(type)
   }

   override fun describeContents(): Int {
      return 0
   }

    companion object CREATOR : Parcelable.Creator<Artist> {
        override fun createFromParcel(parcel: Parcel): Artist {
            return Artist(parcel)
        }

        override fun newArray(size: Int): Array<Artist?> {
            return arrayOfNulls(size)
        }
    }
}
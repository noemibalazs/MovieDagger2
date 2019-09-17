package com.example.moviedagger2.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Movie(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field: SerializedName("overview") val description: String,
    @field:SerializedName("release_date") val releaseDate: String,
    @field:SerializedName("vote_average") val rating: String,
    @field:SerializedName("poster_path") val posterPath: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(description)
        writeString(releaseDate)
        writeString(rating)
        writeString(posterPath)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}
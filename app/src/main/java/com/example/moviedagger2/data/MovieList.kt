package com.example.moviedagger2.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class MovieList(
    @field:SerializedName("results") val movieList: MutableList<Movie>
) : Parcelable {
    constructor(source: Parcel) : this(
        ArrayList<Movie>().apply { source.readList(this, Movie::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(movieList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieList> = object : Parcelable.Creator<MovieList> {
            override fun createFromParcel(source: Parcel): MovieList = MovieList(source)
            override fun newArray(size: Int): Array<MovieList?> = arrayOfNulls(size)
        }
    }
}
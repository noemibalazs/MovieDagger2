package com.example.moviedagger2.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Review(
    @field:SerializedName("author") val author: String,
    @field:SerializedName("content") val content: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(author)
        writeString(content)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Review> = object : Parcelable.Creator<Review> {
            override fun createFromParcel(source: Parcel): Review = Review(source)
            override fun newArray(size: Int): Array<Review?> = arrayOfNulls(size)
        }
    }
}
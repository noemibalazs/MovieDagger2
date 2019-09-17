package com.example.moviedagger2.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class ReviewList(
    @field:SerializedName("results") val reviewList: MutableList<Review>
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(Review.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(reviewList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ReviewList> = object : Parcelable.Creator<ReviewList> {
            override fun createFromParcel(source: Parcel): ReviewList = ReviewList(source)
            override fun newArray(size: Int): Array<ReviewList?> = arrayOfNulls(size)
        }
    }
}
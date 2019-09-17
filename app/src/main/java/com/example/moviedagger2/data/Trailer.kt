package com.example.moviedagger2.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Trailer(
    @field:SerializedName("key") val key: String,
    @field:SerializedName("name") val name: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(key)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Trailer> = object : Parcelable.Creator<Trailer> {
            override fun createFromParcel(source: Parcel): Trailer = Trailer(source)
            override fun newArray(size: Int): Array<Trailer?> = arrayOfNulls(size)
        }
    }
}
package com.example.moviedagger2.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class MovieEntity (

    @PrimaryKey
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field: SerializedName("overview") val description: String,
    @field:SerializedName("release_date") val releaseDate: String,
    @field:SerializedName("vote_average") val rating: String,
    @field:SerializedName("poster_path") val posterPath: String
)
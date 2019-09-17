package com.example.moviedagger2.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getMovieList(): LiveData<MutableList<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovieByID(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie2DB(entity: MovieEntity)
}
package com.example.moviedagger2.helper

import android.content.SharedPreferences
import com.example.moviedagger2.utils.MOVIE_ID

class MySharedPrefHelper(val shared: SharedPreferences) {

    fun saveMovieID(id: Int){
        shared.edit().putInt(MOVIE_ID, id).apply()
    }

    fun getMovieID(): Int = shared.getInt(MOVIE_ID, 0)
}
package com.example.moviedagger2.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.moviedagger2.helper.MySharedPrefHelper
import com.example.moviedagger2.room.MovieDao
import com.example.moviedagger2.room.MovieDataBase
import com.example.moviedagger2.utils.MOVIE_DB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (val app: Application){

    @Provides
    @Singleton
    fun provideContext(): Context = app


    @Provides
    @Singleton
    fun getMovieDB(): MovieDataBase{
        return Room.databaseBuilder(app, MovieDataBase::class.java, MOVIE_DB).build()
    }

    @Provides
    @Singleton
    fun getMovieDAO(db: MovieDataBase): MovieDao{
        return db.getMovieDao()
    }

    @Provides
    @Singleton
    fun getSharedHelper(): MySharedPrefHelper{
        return MySharedPrefHelper(app.getSharedPreferences("My pref", Context.MODE_PRIVATE))
    }


}
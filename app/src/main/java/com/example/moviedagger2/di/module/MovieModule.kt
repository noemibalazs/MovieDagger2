package com.example.moviedagger2.di.module

import com.example.moviedagger2.di.scope.MovieScope
import com.example.moviedagger2.network.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieModule {

    @Provides
    @MovieScope
    fun getMovieService(retrofit: Retrofit):MovieService{
        return retrofit.create(MovieService::class.java)
    }
}
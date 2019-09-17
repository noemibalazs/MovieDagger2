package com.example.moviedagger2.di.component

import com.example.moviedagger2.di.module.AppModule
import com.example.moviedagger2.di.module.RetrofitModule
import com.example.moviedagger2.helper.MySharedPrefHelper
import com.example.moviedagger2.room.MovieDao
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [AppModule::class, RetrofitModule::class])
@Singleton
interface RetrofitComponent {

    fun movieDAO(): MovieDao
    fun retrofit(): Retrofit
    fun sharedPref(): MySharedPrefHelper
}
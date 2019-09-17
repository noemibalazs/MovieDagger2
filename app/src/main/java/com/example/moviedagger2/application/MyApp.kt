package com.example.moviedagger2.application

import android.app.Application
import com.example.moviedagger2.di.component.DaggerMovieComponent
import com.example.moviedagger2.di.component.DaggerRetrofitComponent
import com.example.moviedagger2.di.component.MovieComponent
import com.example.moviedagger2.di.component.RetrofitComponent
import com.example.moviedagger2.di.module.AppModule
import com.example.moviedagger2.di.module.MovieModule
import com.example.moviedagger2.di.module.RetrofitModule

@Suppress("DEPRECATION")
class MyApp : Application() {

    lateinit var retrofitComponent: RetrofitComponent
    lateinit var movieComponent: MovieComponent

    override fun onCreate() {
        super.onCreate()

        retrofitComponent = injectRetrofit(this)
        movieComponent = injectMovieComponent()
    }


    fun injectRetrofit(application: Application): RetrofitComponent {
        return DaggerRetrofitComponent.builder()
            .appModule(AppModule(application))
            .retrofitModule(RetrofitModule())
            .build()
    }

    fun injectMovieComponent(): MovieComponent {
        return DaggerMovieComponent.builder()
            .retrofitComponent(retrofitComponent)
            .movieModule(MovieModule())
            .build()
    }
}
package com.example.moviedagger2.di.component

import com.example.moviedagger2.di.module.MovieModule
import com.example.moviedagger2.di.scope.MovieScope
import com.example.moviedagger2.fragment.FavoriteFragment
import com.example.moviedagger2.fragment.PopularFragment
import com.example.moviedagger2.fragment.TopRatedFragment
import dagger.Component

@Component(dependencies = [RetrofitComponent::class], modules = [MovieModule::class])
@MovieScope
interface MovieComponent {

    fun injectFavorite(favoriteFragment: FavoriteFragment)
    fun injectTopRated(topRatedFragment: TopRatedFragment)
    fun injectPopular(popularFragment: PopularFragment)
}
package com.example.moviedagger2.network

import com.example.moviedagger2.data.MovieList
import com.example.moviedagger2.data.ReviewList
import com.example.moviedagger2.data.TrailerList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") key: String): Observable<MovieList>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") key: String): Observable<MovieList>

    @GET("movie/{id}/videos")
    fun getTrailerList(@Path("id") id: Int, @Query("api_key") key: String): Observable<TrailerList>

    @GET("movie/{id}/reviews")
    fun getReviewList(@Path("id") id: Int, @Query("api_key") key: String): Observable<ReviewList>
}
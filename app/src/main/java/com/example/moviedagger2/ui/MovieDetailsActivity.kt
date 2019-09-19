package com.example.moviedagger2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedagger2.R
import com.example.moviedagger2.application.MyApp
import com.example.moviedagger2.helper.MySharedPrefHelper
import com.example.moviedagger2.network.MovieService
import com.example.moviedagger2.room.MovieDao
import com.example.moviedagger2.room.MovieEntity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.nested_details.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity() {


    @Inject
    @JvmField
    var movieDao: MovieDao?=null

    @Inject
    @JvmField
    var movieService: MovieService?=null

    @Inject
    @JvmField
    var shared: MySharedPrefHelper?= null

    private var compositeDisposable: CompositeDisposable?=null
   // private lateinit var entity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        (this.applicationContext as MyApp).movieComponent.injectDetails(this)
    }


    private fun populateUI(entity: MovieEntity){

    }


    private fun setReviewRv(){
        recycle_review.setHasFixedSize(true)
    }

    private fun setTrailerRv(){
        recycle_trailer.setHasFixedSize(true)
    }
}

package com.example.moviedagger2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.moviedagger2.R
import com.example.moviedagger2.adapter.ReviewAdapter
import com.example.moviedagger2.adapter.TrailerAdapter
import com.example.moviedagger2.application.MyApp
import com.example.moviedagger2.data.Review
import com.example.moviedagger2.data.ReviewList
import com.example.moviedagger2.data.TrailerList
import com.example.moviedagger2.helper.MySharedPrefHelper
import com.example.moviedagger2.network.MovieService
import com.example.moviedagger2.room.MovieDao
import com.example.moviedagger2.room.MovieEntity
import com.example.moviedagger2.utils.KEY
import com.example.moviedagger2.utils.loadPicture
import com.example.moviedagger2.utils.setOnDebounceClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.detail_header.*
import kotlinx.android.synthetic.main.nested_details.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var movieDao: MovieDao? = null

    @Inject
    @JvmField
    var movieService: MovieService? = null

    @Inject
    @JvmField
    var shared: MySharedPrefHelper? = null

    private var compositeDisposable: CompositeDisposable? = null
    private lateinit var entity: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        (this.applicationContext as MyApp).movieComponent.injectDetails(this)
        compositeDisposable = CompositeDisposable()

        val entityObserver = Observer<MovieEntity> {
            entity = it
            populateUI(entity)
        }

        movieDao?.getMovieByID(shared!!.getMovieID())!!.observe(this, entityObserver)

        populateReviews()
        populateTrailers()

        scrollDown()
        scrollUP()

        share()
    }


    private fun populateUI(entity: MovieEntity) {
        label.text = entity.title
        loadPicture(entity.posterPath, background)
        tv_detail_description.text = entity.description
        tv_detail_release_date.text = entity.releaseDate
        tv_detail_user_rating.text = String.format(getString(R.string.rating_builder), entity.rating)
    }


    private fun setReviewRv() {
        recycle_review.setHasFixedSize(true)
    }

    private fun setTrailerRv() {
        recycle_trailer.setHasFixedSize(true)
    }

    private fun loadDataReview(myList: ReviewList){
        recycle_review.adapter = ReviewAdapter(myList.reviewList)
        if (myList.reviewList.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.toast_review), Toast.LENGTH_LONG).show()
        }
    }

    private fun loadDataTrailers(myList: TrailerList){
        recycle_trailer.adapter = TrailerAdapter(myList.trailerList)
        if (myList.trailerList.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.toast_trailer), Toast.LENGTH_LONG).show()
        }
    }

    private fun populateReviews(){
        setReviewRv()
        compositeDisposable?.add(
            movieService?.getReviewList(shared!!.getMovieID(),KEY)!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::loadDataReview)
        )
    }

    private fun populateTrailers(){
        setTrailerRv()
        compositeDisposable?.add(
            movieService?.getTrailerList(shared!!.getMovieID(), KEY)!!.
                    observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::loadDataTrailers)
        )
    }

    private fun scrollDown(){
        down.setOnDebounceClickListener {
            scrollView.fullScroll(ScrollView.FOCUS_DOWN)
        }
    }

    private fun scrollUP(){
        up.setOnDebounceClickListener {
            scrollView.fullScroll(ScrollView.FOCUS_UP)
        }
    }

    private fun sharedInfo(){
        val title = String.format(getString(R.string.shared_movie_title), entity.title)
        val message = String.format(getString(R.string.shared_movie_message), entity.description, entity.rating, entity.releaseDate)
        val intent = Intent(Intent.ACTION_SEND)
        intent.apply {
            type = "plain/text"
            putExtra(Intent.EXTRA_SUBJECT,title)
            putExtra(Intent.EXTRA_TEXT, message)
        }
        if (intent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(intent, getString(R.string.shared_choice)))
        }
    }

    private fun share(){
        fb.setOnDebounceClickListener {
            sharedInfo()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}

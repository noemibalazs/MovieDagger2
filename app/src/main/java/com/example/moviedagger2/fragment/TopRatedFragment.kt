package com.example.moviedagger2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedagger2.R
import com.example.moviedagger2.adapter.CoverAdapter
import com.example.moviedagger2.application.MyApp
import com.example.moviedagger2.data.Movie
import com.example.moviedagger2.data.MovieList
import com.example.moviedagger2.network.MovieService
import com.example.moviedagger2.utils.KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movie_list.*
import javax.inject.Inject

class TopRatedFragment : Fragment() {

    @Inject
    @JvmField
    var movieService: MovieService?=null

    private var compositeDisposable: CompositeDisposable ?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

        (activity?.applicationContext as MyApp).injectMovieComponent().injectTopRated(this)

        setUpRV()
        populateUI()
    }

    private fun setUpRV(){
        cover_recycle_view.setHasFixedSize(true)
    }

    private fun loadData2aAdapter(list: MovieList){
        cover_recycle_view.adapter = CoverAdapter(list.movieList)
    }

    private fun populateUI(){
        compositeDisposable?.add(
            movieService?.getTopRatedMovies(KEY)!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::loadData2aAdapter)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}
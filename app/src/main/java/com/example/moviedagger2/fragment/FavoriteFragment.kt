package com.example.moviedagger2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviedagger2.R
import com.example.moviedagger2.adapter.CoverAdapter
import com.example.moviedagger2.application.MyApp
import com.example.moviedagger2.data.Movie
import com.example.moviedagger2.room.MovieDao
import com.example.moviedagger2.utils.entity2Movie
import kotlinx.android.synthetic.main.movie_list.*
import javax.inject.Inject

class FavoriteFragment: Fragment() {

    @Inject
    @JvmField
    var movieDao:MovieDao?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.applicationContext as MyApp).movieComponent.injectFavorite(this)
        populateUI()
    }

    private fun populateUI(){
        cover_recycle_view.setHasFixedSize(true)
        val movieList:MutableList<Movie> = mutableListOf()

        movieDao?.getMovieList()!!.observe(this, Observer {list ->
            list.forEach{ movieList.add(entity2Movie(it)) }
            cover_recycle_view.adapter = CoverAdapter(movieList)
        })
    }

}
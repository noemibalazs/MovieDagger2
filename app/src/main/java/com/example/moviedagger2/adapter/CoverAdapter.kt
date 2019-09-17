package com.example.moviedagger2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedagger2.R
import com.example.moviedagger2.application.MyApp
import com.example.moviedagger2.data.Movie
import com.example.moviedagger2.helper.MySharedPrefHelper
import com.example.moviedagger2.room.MovieDao
import com.example.moviedagger2.room.MovieEntity
import com.example.moviedagger2.ui.MovieDetailsActivity
import com.example.moviedagger2.utils.*
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class CoverAdapter(val myList: MutableList<Movie>) :
    RecyclerView.Adapter<CoverAdapter.CustomViewHolder>() {

    @Inject
    @JvmField
    var moviedDao: MovieDao? = null

    @Inject
    @JvmField
    var sharedHlper: MySharedPrefHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_cover, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        moviedDao = (holder.itemView.context.applicationContext as MyApp).retrofitComponent.movieDAO()
        sharedHlper = (holder.itemView.context.applicationContext as MyApp).retrofitComponent.sharedPref()

        val movie = myList[position]

        if (movie.posterPath == null) {
            holder.imageHolder.context.loadPicture(
                holder.imageHolder.context.getDrawableUri(),
                holder.imageHolder
            )
        } else {
            holder.imageHolder.context.loadPicture(
                getMoviePoster(movie.posterPath),
                holder.imageHolder
            )
        }

        holder.imageHolder.setOnDebounceClickListener {

            val entity = holder.imageHolder.context.movie2Entity(movie)
            sharedHlper?.saveMovieID(entity.id)
            addMovie2DB(entity)
            holder.imageHolder.context.openActivity(MovieDetailsActivity::class.java)
        }

    }

    private fun addMovie2DB(entity: MovieEntity){
        doAsync {
            moviedDao?.addMovie2DB(entity)
        }
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageHolder = view.findViewById<ImageView>(R.id.favorite_image)
    }

}
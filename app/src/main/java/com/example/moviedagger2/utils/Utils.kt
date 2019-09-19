package com.example.moviedagger2.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.moviedagger2.R
import com.example.moviedagger2.data.Movie
import com.example.moviedagger2.helper.DebounceClickListener
import com.example.moviedagger2.room.MovieEntity


fun getMoviePoster(posterPath: String): String{
    return POSTER_URL + posterPath
}

fun getMovieYoutubePath(key: String):String{
    return YOUTUBE_PATH + key
}

fun getYoutubeScreenShot(path:String):String{
    return YOUTUBE_START + path + YOUTUBE_END
}

fun Context.loadPicture(link:String, view: ImageView) {
    Glide.with(this)
        .load(link)
        .placeholder(R.drawable.miss_sloane)
        .error(R.drawable.miss_sloane)
        .into(view)
}

fun Context.getDrawableUri(): String{
    val path = Uri.parse("android.resource://" + this.packageName + "drawable/miss_sloane")
    return path.toString()
}

fun <T> Context.openActivity(dest: Class<T>){
    val  intent = Intent(this, dest)
    this.startActivity(intent)
}

fun Context.movie2Entity(movie: Movie): MovieEntity {
    if (movie.posterPath == null){
        return MovieEntity(movie.id, movie.title, movie.description, movie.releaseDate, movie.rating, this.getDrawableUri())
    }else{
        return MovieEntity(movie.id, movie.title, movie.description, movie.releaseDate, movie.rating, getMoviePoster(movie.posterPath))
    }
}

fun View.setOnDebounceClickListener(clicked: (View) -> Unit){
    this.setOnClickListener(object : DebounceClickListener(){
        override fun onDebounce(v: View) {
            clicked(v)
        }

    })
}
fun String.drop(text:String):String{
    return text.drop(31)
}

fun entity2Movie(entity: MovieEntity): Movie{
    return if (entity.posterPath.startsWith(POSTER_URL)){
        Movie(entity.id, entity.title, entity.description, entity.releaseDate, entity.rating, entity.posterPath.drop(entity.posterPath))
    }else{
        Movie(entity.id, entity.title, entity.description, entity.releaseDate, entity.rating, entity.posterPath)
    }
}
package com.example.moviedagger2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedagger2.R
import com.example.moviedagger2.data.Review

class ReviewAdapter(val context: Context, val reviews:MutableList<Review>): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.author.text = review.author
        holder.comment.text = review.content
    }

    inner class ReviewViewHolder(view: View): RecyclerView.ViewHolder(view){
        val author = view.findViewById<TextView>(R.id.review_author)
        val comment = view.findViewById<TextView>(R.id.review_comment)
    }
}
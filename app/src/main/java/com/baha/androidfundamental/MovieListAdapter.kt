package com.baha.androidfundamental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter() :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = movies.size



    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ImageView = itemView.findViewById(R.id.ivPosterPhoto)
        private val movieName: TextView = itemView.findViewById(R.id.tvMovieName)
        private val movieDuration: TextView = itemView.findViewById(R.id.tvMovieTime)
        private val movieGenre: TextView = itemView.findViewById(R.id.tvGenre)



    }

}
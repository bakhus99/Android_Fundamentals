package com.baha.androidfundamental.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.R
import com.baha.androidfundamental.data.Movie

class MovieListAdapter(private val clickListener: ItemClickListener ) :
    RecyclerView.Adapter<MovieListViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {

        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovie(newMovie: List<Movie>) {
        movies = newMovie
        notifyDataSetChanged()
    }

    fun interface ItemClickListener {
        fun onClick(movie: Movie)
    }
}

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val moviePoster: ImageView = itemView.findViewById(R.id.ivPosterPhoto)
        private val movieName: TextView = itemView.findViewById(R.id.tvMovieName)
        private val movieDuration: TextView = itemView.findViewById(R.id.tvMovieTime)
        private val movieGenre: TextView = itemView.findViewById(R.id.tvGenre)
        private val moviePg:TextView = itemView.findViewById(R.id.tvPg)
        private val movieReviews:TextView = itemView.findViewById(R.id.tvReviews)


        fun bind(movie: Movie) {
            moviePoster.setImageResource(movie.moviePosterPhoto)
            movieName.text = movie.movieName
            movieDuration.text = movie.movieDuration
            movieGenre.text = movie.movieGenre
            moviePg.text = movie.moviePg
            movieReviews.text = movie.movieReviews
        }
    }



package com.baha.androidfundamental.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.R
import com.baha.androidfundamental.data.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MovieListAdapter() :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var movies = listOf<Movie>()
    var onMovieClickListener: ItemClickListener? = null

    fun bindMovie(newMovie: List<Movie>) {
        movies = newMovie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            onMovieClickListener?.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    fun interface ItemClickListener {
        fun onClick(movie: Movie)
    }

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePoster: ImageView = itemView.findViewById(R.id.ivPosterPhoto)
    private val movieName: TextView = itemView.findViewById(R.id.tvMovieName)
    private val movieDuration: TextView = itemView.findViewById(R.id.tvMovieTime)
    private val movieGenre: TextView = itemView.findViewById(R.id.tvGenre)
    private val moviePg: TextView = itemView.findViewById(R.id.tvPg)
    private val movieReviews: TextView = itemView.findViewById(R.id.tvReviews)
    private val movieRating: RatingBar = itemView.findViewById(R.id.ratingBar)

    fun bind(movie: Movie) {
        Glide.with(itemView.context)
            .load(movie.poster)
            .transform(
                RoundedCorners(
                    itemView.context.resources.getDimension(
                        R.dimen.small
                    ).toInt()
                )
            )
            .into(moviePoster)
        movieName.text = movie.title
        movieDuration.text =
            itemView.context.getString(R.string.movie_duration, movie.runtime.toString())
        movieGenre.text = movie.genres.toString().replace("[", "").replace("]", "")
        moviePg.text = itemView.context.getString(
            R.string.age,
            if (movie.minimumAge) itemView.context.getString(
                R.string.pg16
            ) else itemView.context.getString(
                R.string.pg13
            )
        )
        movieReviews.text =
            itemView.context.getString(R.string.reviews, movie.numberOfRatings.toString())
        movieRating.progress = movie.ratings.toInt()
    }
}
}



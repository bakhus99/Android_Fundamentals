package com.baha.androidfundamental.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.R
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.databinding.ViewHolderMoviesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MovieListAdapter() :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var movies = listOf<Movie>()
    var onMovieClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ViewHolderMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            onMovieClickListener?.onClick(movies[position])
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

    class MovieListViewHolder(private val binding: ViewHolderMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
                .into(binding.ivPosterPhoto)
            binding.tvMovieName.text = movie.title
            binding.tvMovieTime.text =
                itemView.context.getString(R.string.movie_duration, movie.runtime.toString())
            binding.tvGenre.text = movie.genres.toString().replace("[", "").replace("]", "")
            binding.tvPg.text = itemView.context.getString(
                R.string.age,
                if (movie.minimumAge) itemView.context.getString(
                    R.string.pg16
                ) else itemView.context.getString(
                    R.string.pg13
                )
            )
            binding.tvReviews.text =
                itemView.context.getString(R.string.reviews, movie.numberOfRatings.toString())
            binding.ratingBar.progress = movie.ratings.toInt()
    }
}
}



package com.baha.androidfundamental.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.loadMovies
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.ActorAdapter
import com.baha.androidfundamental.data.Movie
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesDetails : Fragment() {

    private var coroutineScope: CoroutineScope? = null
    private var recycler: RecyclerView? = null
    private val adapter = ActorAdapter()
    private lateinit var movieBackdrop: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var rating: RatingBar
    private lateinit var tvGenre: TextView
    private lateinit var tvReview: TextView
    private lateinit var tvOverview: TextView
    private lateinit var tvAge: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        loadActorsFromjson()
    }

    private fun loadActorsFromjson() {
        coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope?.launch {
            val movie = arguments?.getParcelable<Movie>("movie")?.let { findMovie(it) }
            if (movie != null) {
                withContext(Dispatchers.Main) {
                    adapter.bindActors(movie.actors)
                    bindMovie(movie)
                }
            }
        }
    }

    private fun initView(view: View) {
        movieBackdrop = view.findViewById(R.id.backDropMovie)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvGenre = view.findViewById(R.id.movieGenre)
        tvOverview = view.findViewById(R.id.overview)
        tvReview = view.findViewById(R.id.tvReviews)
        tvAge = view.findViewById(R.id.tvAge)
        rating = view.findViewById(R.id.ratingBar)
    }

    private fun bindMovie(movie: Movie) {
        Glide.with(requireContext())
            .load(movie.backdrop)
            .into(movieBackdrop)
        tvTitle.text = movie.title
        tvGenre.text = movie.genres.toString().replace("[", "").replace("]", "")
        tvOverview.text = movie.overview
        tvReview.text = getString(R.string.reviews, movie.numberOfRatings.toString())
        tvAge.text = getString(
            R.string.age,
            if (movie.minimumAge) getString(
                R.string.pg16
            ) else getString(
                R.string.pg13
            )
        )
        rating.progress = movie.ratings.toInt()
    }

    private suspend fun findMovie(movie: Movie): Movie? {
        return loadMovies(requireContext()).find { it == movie }
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val movieFragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putParcelable("movie", movie)
            movieFragment.arguments = bundle
            return movieFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        recycler = view.findViewById(R.id.rcActors)
        recycler?.adapter = adapter
        view.findViewById<LinearLayout>(R.id.linearLayoutBack).setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }
}
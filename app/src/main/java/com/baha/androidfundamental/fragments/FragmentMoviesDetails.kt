package com.baha.androidfundamental.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.AsettMovieRepo
import com.baha.androidfundamental.MoviesDetailsFactory
import com.baha.androidfundamental.MoviesRepository
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.ActorAdapter
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.databinding.FragmentMoviesDetailsBinding
import com.baha.androidfundamental.models.MoviesDetailsViewModel
import com.bumptech.glide.Glide

private const val MOVIE = "movie"

class FragmentMoviesDetails : Fragment() {

    private var recycler: RecyclerView? = null
    private val adapter = ActorAdapter()
    private lateinit var viewModelFactory: MoviesDetailsFactory
    private lateinit var moviesRepository: MoviesRepository
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.rcActors
        recycler?.adapter = adapter
        binding.linearLayoutBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun onStart() {
        super.onStart()
        loadActorsFromJson()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadActorsFromJson() {
        moviesRepository = AsettMovieRepo(requireContext())
        viewModelFactory = MoviesDetailsFactory(
            moviesRepository,
            arguments?.getParcelable<Movie>(MOVIE)?.let { (it) }!!
        )
        val viewModelMovie = ViewModelProvider(
            this, viewModelFactory
        ).get(MoviesDetailsViewModel::class.java)
        viewModelMovie.addMovie()
        viewModelMovie.movie.observe(viewLifecycleOwner) {
            bindMovie(it)
        }
        viewModelMovie.actors.observe(viewLifecycleOwner) {
            adapter.bindActors(it)
        }
    }

    private fun bindMovie(movie: Movie) {
        Glide.with(requireContext())
            .load(movie.backdrop)
            .into(binding.backDropMovie)
        binding.tvTitle.text = movie.title
        binding.movieGenre.text = movie.genres.toString().replace("[", "").replace("]", "")
        binding.overview.text = movie.overview
        binding.tvReviews.text = getString(R.string.reviews, movie.numberOfRatings.toString())
        binding.tvAge.text = getString(
            R.string.age,
            if (movie.minimumAge) getString(
                R.string.pg16
            ) else getString(
                R.string.pg13
            )
        )
        binding.ratingBar.progress = movie.ratings.toInt()
        binding.cast.visibility = if (movie.actors.isEmpty()) View.GONE else View.VISIBLE
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val movieFragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE, movie)
            movieFragment.arguments = bundle
            return movieFragment
        }
    }
}
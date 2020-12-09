package com.baha.androidfundamental.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.MovieListAdapter
import com.baha.androidfundamental.data.Movie

class FragmentMoviesList : Fragment() {

    private var recycler: RecyclerView? = null
    private var movieList = listOf<Movie>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieList = listOf(
            Movie(
                getString(R.string.avengers_end_game),
                getString(R.string.movie_duration),
                getString(R.string.movie_genre),
                getString(R.string.reviews),
                getString(R.string.pg13),
                R.drawable.avg
            ), Movie(
                "Tenet",
                "97 min",
                getString(R.string.movie_genre),
                "98 reviews",
                getString(R.string.pg16),
                R.drawable.tenet
            ), Movie(
                "Black Widow",
                "102 min",
                getString(R.string.movie_genre),
                "38 reviews",
                getString(R.string.pg13),
                R.drawable.bw
            ), Movie(
                "Wonder Woman 1984",
                "120 min",
                getString(R.string.movie_genre),
                "74 reviews",
                getString(R.string.pg13),
                R.drawable.ww
            )
        )
    }

    override fun onDetach() {
        recycler = null
        super.onDetach()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MovieListAdapter)?.apply {
            bindMovie(movieList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById<RecyclerView>(R.id.recyclerViewMovieList)
        recycler?.adapter = MovieListAdapter(clickListener)
        view.findViewById<RecyclerView>(R.id.recyclerViewMovieList).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.frame, FragmentMoviesDetails())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private val clickListener = object : MovieListAdapter.OnRecyclerItemClicked{
        override fun onClick(movie: Movie) {
            doClick(movie)
        }
    }

    private fun doClick(movie: Movie) {
        recycler?.let { rv->
            fragmentManager?.beginTransaction()
                ?.replace(R.id.frame, FragmentMoviesDetails())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}
package com.baha.androidfundamental

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MovieListAdapter
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    private val movie = listOf(
        Movie(
            getString(R.string.avengers_end_game),
            getString(R.string.movie_duration),
            getString(R.string.movie_genre),
            R.drawable.avg
        )
    )

    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onDetach() {
        recycler = null
        super.onDetach()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler?.adapter = adapter

        recycler = view.findViewById(R.id.recyclerViewMovieList)
        adapter = MovieListAdapter()


        // view.findViewById<RecyclerView>(R.id.recyclerViewMovieList)
//            .setOnClickListener {
//            fragmentManager?.beginTransaction()
//                ?.replace(R.id.frame, FragmentMoviesDetails())
//                ?.addToBackStack(null)
//                ?.commit()
//        }
    }

    private fun updateData() {
        (recycler?.adapter as? MovieListAdapter)?.apply {
            bindActors(movie)
        }
    }
}
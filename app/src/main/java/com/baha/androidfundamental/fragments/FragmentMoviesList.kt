package com.baha.androidfundamental.fragments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.MoviesList
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.MovieListAdapter
import com.baha.androidfundamental.data.Movie

class FragmentMoviesList : Fragment() {

    private var recycler: RecyclerView? = null
    private var movieList = MoviesList.getMovieList()
    private val adapter = MovieListAdapter()

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
        adapter.bindMovie(movieList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById<RecyclerView>(R.id.recyclerViewMovieList)
        adapter.onMovieClickListener = clickListener
        recycler?.adapter = adapter
        val manager = GridLayoutManager(
            context,
            getColumns(
                requireContext(),
                requireContext().resources.getDimension(R.dimen.scalingFactor)
            )
        )
        recycler?.layoutManager = manager
    }

    private val clickListener =
        MovieListAdapter.ItemClickListener { movie -> onClick(movie) }

    private fun onClick(movie: Movie) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frame, FragmentMoviesDetails())
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }

    private fun getColumns(context: Context, columnWidthDp: Float): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels
        return (dpWidth / columnWidthDp).toInt()
    }
}

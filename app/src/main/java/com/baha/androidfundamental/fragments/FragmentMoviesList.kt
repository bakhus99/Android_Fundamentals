package com.baha.androidfundamental.fragments

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.MoviesList
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.MovieListAdapter
import com.baha.androidfundamental.data.Movie
import com.bumptech.glide.load.engine.Resource

class FragmentMoviesList : Fragment() {


    private var recycler: RecyclerView? = null
    private var movieList = MoviesList.getMovieList()

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
        getColumns()
    }

    private fun getColumns(): Int {
        val displayMetrics:DisplayMetrics = Resources.getSystem().displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 180
        return (dpWidth/scalingFactor).toInt()
    }

    private val clickListener =
        MovieListAdapter.ItemClickListener { movie -> doClick(movie) }

    private fun doClick(movie: Movie) {

        fragmentManager?.beginTransaction()
            ?.replace(R.id.frame, FragmentMoviesDetails())
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }


}
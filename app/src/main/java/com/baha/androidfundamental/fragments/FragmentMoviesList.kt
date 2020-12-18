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
import com.android.academy.fundamentals.homework.features.data.loadMovies
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.MovieListAdapter
import com.baha.androidfundamental.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesList : Fragment() {

    private var recycler: RecyclerView? = null

    private var movieList = MovieListAdapter()
    private val adapter = MovieListAdapter()
    private var coroutineScope: CoroutineScope? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)

    }

    override fun onStart() {
        super.onStart()
//        updateData()
        loadMovieFromJson()
    }

    private fun loadMovieFromJson(){
        coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope?.launch {
            val movies = loadMovies(requireContext())
            withContext(Dispatchers.Main){
                adapter.bindMovie(movies)
            }
        }
    }

//    private fun updateData() {
//        adapter.bindMovie(movieList)
//    }

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
        MovieListAdapter.ItemClickListener { movie -> onClick(movie,id) }

    private fun onClick(movie: Movie,movieId:Int) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frame, FragmentMoviesDetails.newInstance(movieId))
            ?.addToBackStack(null)
            ?.commit()
    }

//    companion object {
//        fun newInstance() = FragmentMoviesList()
//    }

    private fun getColumns(context: Context, columnWidthDp: Float): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels
        return (dpWidth / columnWidthDp).toInt()
    }
}

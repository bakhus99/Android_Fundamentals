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
import com.baha.androidfundamental.MoviesListViewModel
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.MovieListAdapter
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.loadMovies
import com.baha.androidfundamental.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {

    private var recycler: RecyclerView? = null
    private val adapter = MovieListAdapter()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val viewModel = MoviesListViewModel()
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.recyclerViewMovieList
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

    override fun onStart() {
        super.onStart()
        loadMovieFromJson()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadMovieFromJson() {
        coroutineScope.launch {
            val movies = loadMovies(requireContext())
            withContext(Dispatchers.Main) {
                adapter.bindMovie(movies)
            }
        }
    }

    private val clickListener =
        MovieListAdapter.ItemClickListener { movie -> onClick(movie) }

    private fun onClick(movie: Movie) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.frame, FragmentMoviesDetails.newInstance(movie))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun getColumns(context: Context, columnWidthDp: Float): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels
        return (dpWidth / columnWidthDp).toInt()
    }
}

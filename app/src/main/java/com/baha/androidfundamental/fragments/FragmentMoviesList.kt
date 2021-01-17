package com.baha.androidfundamental.fragments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.MoviesListFactory
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.MovieListAdapter
import com.baha.androidfundamental.data.Movie
import com.baha.androidfundamental.data.NetworkModule
import com.baha.androidfundamental.databinding.FragmentMoviesListBinding
import com.baha.androidfundamental.models.MoviesListViewModel
import com.baha.androidfundamental.repositories.MoviesRepository
import com.baha.androidfundamental.repositories.NetworkMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

private const val API_KEY = "8f43ee4b8e24bbbcb9e8c7efc02e8879"

class FragmentMoviesList : Fragment() {

    private var recycler: RecyclerView? = null
    private val adapter = MovieListAdapter()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var moviesRepository: MoviesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesListBinding.bind(view)
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
        moviesRepository = NetworkMovieRepository(NetworkModule.api)
        val viewModel =
            ViewModelProvider(this, MoviesListFactory(moviesRepository)).get(
                MoviesListViewModel::class.java
            )


        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.bindMovie(it)
        }
        viewModel.loadMovieJson()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
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

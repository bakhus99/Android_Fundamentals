package com.baha.androidfundamental.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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

    //private val actors = ActorsList.getActorsList()
    private val adapter = ActorAdapter()
    private lateinit var movieBackdrop:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        initView(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        loadActorsFromjson()
        // updateData()
    }

    private fun updateData() {
        //adapter.bindActors(actors)
    }


    private fun loadActorsFromjson() {
        coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope?.launch {
            val movie = findMovie(arguments?.getInt(ID))
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

    }

    private fun bindMovie(movie: Movie) {
        Glide.with(requireContext())
            .load(movie.backdrop)
            .into(movieBackdrop)


    }

    private suspend fun findMovie(movieId: Int?): Movie? {
        return loadMovies(requireContext()).find { it.id == movieId }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rcActors)
        recycler?.adapter = adapter
        view.findViewById<LinearLayout>(R.id.linearLayoutBack).setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    companion object {
        private const val ID = "ID"

        fun newInstance(movieId: Int): FragmentMoviesDetails {
            val movieFragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putInt(ID, movieId)
            movieFragment.arguments = bundle
            return movieFragment
        }

    }

}
package com.baha.androidfundamental.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.ActorsList
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.ActorAdapter
import com.baha.androidfundamental.data.Movie

class FragmentMoviesDetails : Fragment() {

    private var recycler: RecyclerView? = null
    private val actors = ActorsList.getActorsList()
    private val adapter = ActorAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }
    
    private fun updateData(){
        adapter.bindActors(actors)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rcActors)
        recycler?.adapter = adapter
        view.findViewById<LinearLayout>(R.id.linearLayoutBack).setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }
}
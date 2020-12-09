package com.baha.androidfundamental.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.baha.androidfundamental.R
import com.baha.androidfundamental.adapters.ActorAdapter
import com.baha.androidfundamental.data.Actor

class FragmentMoviesDetails : Fragment() {

    private var recycler: RecyclerView? = null
    private var actorsList = listOf<Actor>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        actorsList = listOf(
            Actor(
                getString(R.string.robert_downey_jr),
                R.drawable.robert_jr
            ), Actor(
                getString(R.string.chris_evans),
                R.drawable.chris_e
            ), Actor(
                getString(R.string.mark_ruffalo),
                R.drawable.mark_r
            ), Actor(
                getString(R.string.chris_hemsworth),
                R.drawable.chris_h
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
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }
    private fun updateData(){
        (recycler?.adapter as ActorAdapter)?.apply {
            bindActors(actorsList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rcActors)
        recycler?.adapter = ActorAdapter()
        view.findViewById<LinearLayout>(R.id.linearLayoutBack).setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }
}
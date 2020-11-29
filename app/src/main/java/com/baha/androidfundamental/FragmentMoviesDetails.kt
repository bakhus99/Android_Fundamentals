package com.baha.androidfundamental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class FragmentMoviesDetails :Fragment(){
    private var onMovieBackClickedListener: MovieBackClickListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onMovieBackClickedListener = context as? MovieBackClickListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.linearLayoutBack).setOnClickListener {
            onMovieBackClickedListener?.onMovieBackClicked()
        }
    }
    interface MovieBackClickListener {
        fun onMovieBackClicked()
    }
}
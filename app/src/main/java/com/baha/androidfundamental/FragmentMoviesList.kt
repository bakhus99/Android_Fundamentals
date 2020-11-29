package com.baha.androidfundamental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {
    private var onMovieClickedListener: MovieClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onMovieClickedListener = context as? MovieClickListener
    }
    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.bgPoster).setOnClickListener {
            onMovieClickedListener?.onMovieClicked()
        }
    }

    interface MovieClickListener {
        fun onMovieClicked()
    }
}
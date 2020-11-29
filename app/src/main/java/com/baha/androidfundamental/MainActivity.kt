package com.baha.androidfundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(),FragmentMoviesList.MovieClickListener,FragmentMoviesDetails.MovieBackClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame,FragmentMoviesList())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onMovieClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame,FragmentMoviesDetails())
            .addToBackStack(null)
            .commit()
    }

    override fun onMovieBackClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame,FragmentMoviesList())
            .addToBackStack(null)
            .commit()
    }


}
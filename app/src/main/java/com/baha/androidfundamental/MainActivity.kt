package com.baha.androidfundamental

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baha.androidfundamental.fragments.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, FragmentMoviesList())
                .commit()
        }
    }
}
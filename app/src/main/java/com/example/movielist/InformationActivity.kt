package com.example.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movielist.model.Results

import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.content_information.*
import kotlinx.android.synthetic.main.movie_item.view.*

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        var movieItem : Results = intent.getParcelableExtra("movieItem")
        tvTitle.text = movieItem.title
        tvOverview.text = movieItem.overview
        tvRating.text = movieItem.vote_average.toString()
        tvReleaseDate.text = getString(R.string.release_date, movieItem.release_date)

        Glide.with(this).load(movieItem.getPosterUrl()).into(ivPoster)
        Glide.with(this).load(movieItem.getBackdropUrl()).into(ivBackdrop)
    }

}

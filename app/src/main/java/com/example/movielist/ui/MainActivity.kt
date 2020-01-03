package com.example.movielist.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielist.MainActivityViewModel
import com.example.movielist.MovieAdapter
import com.example.movielist.model.Results

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.content.Intent
import com.example.movielist.InformationActivity
import com.example.movielist.R


class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Results>()
    private val movieAdapter = MovieAdapter(movies) { movieItem -> onMovieClick(movieItem) }
    private lateinit var viewModel : MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            viewModel.getMovieList(etYear.text.toString().toInt())
        }
        rvMovies.layoutManager = GridLayoutManager(this, 2)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.movieList.observe(this, Observer {
            movies.clear()
            movies.addAll(it.results)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun onMovieClick(movieItem: Results) {
        Snackbar.make(rvMovies, "This movie is ${movieItem.title}", Snackbar.LENGTH_LONG).show()
        val informationIntent = Intent(this, InformationActivity::class.java)
        informationIntent.putExtra("movieItem", movieItem)
        startActivity(informationIntent)
    }
}

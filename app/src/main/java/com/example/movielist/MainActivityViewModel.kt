package com.example.movielist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movielist.model.MovieList
import com.example.movielist.model.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    val movieList = MutableLiveData<MovieList>()
    val error = MutableLiveData<String>()

    /**
     * Get a random number trivia from the repository using Retrofit.
     * onResponse if the response is successful populate the [movieList] object.
     * If the call encountered an error then populate the [error] object.
     */
    fun getMovieList(year : Int) {
        movieRepository.getMovies(year).enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful) {
                    movieList.value = response.body()
                }
                else {
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                error.value = t.message
            }
        })
    }

}


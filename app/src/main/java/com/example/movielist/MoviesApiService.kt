package com.example.movielist

import android.util.Log
import com.example.movielist.model.MovieList
import retrofit2.Call
import retrofit2.http.GET

public interface MoviesApiService {
    @GET("/3/discover/movie?api_key=83340ca6a12de1d42b33fac409627dde&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&year=2019")
    fun getMovies(): Call<MovieList>
}
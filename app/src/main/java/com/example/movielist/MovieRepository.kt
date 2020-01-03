package com.example.movielist

class MovieRepository {
    private val moviesApi: MoviesApiService = MoviesApi.createApi()

    fun getMovies(year: Int) = moviesApi.getMovies(year)
}


package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import com.lucassimoesmartins.flixguide.constant.Constants
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.model.Movie
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.webclient.WebClient

class MovieRepository(
    private val webClient: WebClient,
    private val movieDao: MovieDao
) {

    val movieList: LiveData<List<Movie>> = movieDao.getMovies

    suspend fun getPopularMovies() {
        try {
            val movieResponse: MovieResponse = webClient.getPopularMovies()
            movieDao.insertMovieList(movieResponse.results)
        } catch (error: Exception) {
            throw Exception(Constants.GENERIC_FAIL_MESSAGE, error)
        }
    }
}
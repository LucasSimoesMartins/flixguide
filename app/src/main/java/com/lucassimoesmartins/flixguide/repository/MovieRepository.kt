package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.webclient.WebClient
import com.lucassimoesmartins.flixguide.predefined.MovieCategory

const val GENERIC_FAIL_MESSAGE = "Sorry, something went wrong, come back later"

class MovieRepository(
    private val webClient: WebClient,
    private val movieDao: MovieDao
) {

    val imgFeaturedMovie: LiveData<String> = movieDao.getImgFeaturedMovie
    val imgPopularMovieList: LiveData<List<String>> = movieDao.getImgPopularMovieList

    suspend fun getMovies() {
        try {
            val movieResponse: MovieResponse = webClient.getPopularMovies()
            movieResponse.results.forEach { movie ->
                movie.category = MovieCategory.POPULAR
            }
            movieDao.insertMovieList(movieResponse.results)
        } catch (error: Exception) {
            throw Exception(GENERIC_FAIL_MESSAGE, error)
        }
    }
}
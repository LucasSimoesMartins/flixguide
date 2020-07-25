package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.model.MovieCategoryCrossRef
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.webclient.WebClient
import com.lucassimoesmartins.flixguide.predefined.CategoryEnum

const val GENERIC_FAIL_MESSAGE = "Sorry, something went wrong, come back later"

class MovieRepository(
    private val webClient: WebClient,
    private val movieDao: MovieDao
) {

    val imgFeaturedMovie: LiveData<String> = movieDao.getImgFeaturedMovie
    fun getImgMovieList(categoryEnum: CategoryEnum) = movieDao.getImgMovieList(categoryEnum.id)

    suspend fun getMovies() {
        try {
            val movieCategoryCrossRefList = arrayListOf<MovieCategoryCrossRef>()

            val movieResponse: MovieResponse = webClient.getPopularMovies()

            movieResponse.results.forEach { movie ->
                movieCategoryCrossRefList.add(MovieCategoryCrossRef(movieId = movie.id, categoryId = CategoryEnum.POPULAR.id))
            }

            movieDao.insertMovieCategoryCrossRefList(movieCategoryCrossRefList)
            movieDao.insertMovieList(movieResponse.results)
        } catch (error: Exception) {
            throw Exception(GENERIC_FAIL_MESSAGE, error)
        }
    }
}
package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.model.Category
import com.lucassimoesmartins.flixguide.model.MovieCategoryCrossRef
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.webclient.WebClient

const val GENERIC_FAIL_MESSAGE = "Sorry, something went wrong, come back later"

class MovieRepository(
    private val webClient: WebClient,
    private val movieDao: MovieDao
) {

    val imgFeaturedMovie: LiveData<String> = movieDao.getImgFeaturedMovie
    fun getImgMovieList(categoryId: Int) = movieDao.getImgMovieList(categoryId)

    suspend fun getMovies() {
        try {
            movieDao.insertCategoryList(listOf(Category(1,"popular"), Category(2, "top_rated")))

            val movieResponse: MovieResponse = webClient.getPopularMovies()

            val movieCategoryCrossRefList = arrayListOf<MovieCategoryCrossRef>()
            movieResponse.results.forEach { movie ->
                movieCategoryCrossRefList.add(MovieCategoryCrossRef(movieId = movie.id, categoryId = 1))
            }

            movieDao.insertMovieCategoryCrossRefList(movieCategoryCrossRefList)
            movieDao.insertMovieList(movieResponse.results)
        } catch (error: Exception) {
            throw Exception(GENERIC_FAIL_MESSAGE, error)
        }
    }
}
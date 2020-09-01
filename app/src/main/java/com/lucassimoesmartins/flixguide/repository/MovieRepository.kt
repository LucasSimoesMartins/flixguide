package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.model.Movie
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
            val movieResponse = MovieResponse(-1, arrayListOf(),-1,-1)
            val movieCategoryCrossRefList: ArrayList<MovieCategoryCrossRef> = arrayListOf()

            addMovieCategoryCrossRefList(CategoryEnum.POPULAR, webClient.getPopularMovieList().results, movieResponse, movieCategoryCrossRefList)
            addMovieCategoryCrossRefList(CategoryEnum.TOP_RATED, webClient.getTopRatedMovieList().results, movieResponse, movieCategoryCrossRefList)
            addMovieCategoryCrossRefList(CategoryEnum.TOP_10_TODAY, webClient.getTopTenTodayMovieList().results, movieResponse, movieCategoryCrossRefList)
            addMovieCategoryCrossRefList(CategoryEnum.NOW_PLAYING, webClient.getNowPlayingMovieList().results, movieResponse, movieCategoryCrossRefList)
            addMovieCategoryCrossRefList(CategoryEnum.UPCOMING, webClient.getUpcomingMovieList().results, movieResponse, movieCategoryCrossRefList)
            addMovieCategoryCrossRefList(CategoryEnum.OLD, webClient.getOldMovieList().results, movieResponse, movieCategoryCrossRefList)

            movieDao.deleteAllMovies()
            movieDao.insertMovieCategoryCrossRefList(movieCategoryCrossRefList)
            movieDao.insertMovieList(movieResponse.results)
        } catch (error: Exception) {
            throw Exception(GENERIC_FAIL_MESSAGE, error)
        }
    }

    private fun addMovieCategoryCrossRefList(categoryEnum: CategoryEnum, movieList: ArrayList<Movie>, movieResponse: MovieResponse, movieCategoryCrossRefList: ArrayList<MovieCategoryCrossRef>) {
        movieResponse.results.addAll(movieList.apply {
            this.forEach { movie ->
                movieCategoryCrossRefList.add(MovieCategoryCrossRef(movieId = movie.id, categoryId = categoryEnum.id))
            }
        })
    }
}
package com.lucassimoesmartins.flixguide.network.service

import com.lucassimoesmartins.flixguide.BuildConfig
import com.lucassimoesmartins.flixguide.model.MovieResponse
import retrofit2.http.GET

const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY

interface Service {

    @GET("movie/popular?api_key=${TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getPopularMovieList(): MovieResponse

    @GET("movie/top_rated?api_key=${TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getTopRatedMovieList(): MovieResponse

    @GET("trending/movie/day?api_key=${TMDB_API_KEY}")
    suspend fun getTopTenTodayMovieList(): MovieResponse

    @GET("movie/now_playing?api_key=${TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getNowPlayingMovieList(): MovieResponse

    @GET("movie/upcoming?api_key=${TMDB_API_KEY}&language=en-US&page=1")
    suspend fun getUpcomingMovieList(): MovieResponse

    @GET("discover/movie?api_key=${TMDB_API_KEY}&language=en-US&page=1&sort_by=popularity.desc&release_date.gte=1950-01-01&release_date.lte=1974-12-31")
    suspend fun getOldMovieList(): MovieResponse

}
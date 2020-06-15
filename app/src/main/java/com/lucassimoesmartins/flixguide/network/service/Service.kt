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

}
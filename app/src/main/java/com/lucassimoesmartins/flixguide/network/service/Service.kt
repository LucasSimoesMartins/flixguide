package com.lucassimoesmartins.flixguide.network.service

import com.lucassimoesmartins.flixguide.constant.Constants
import com.lucassimoesmartins.flixguide.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("movie/popular?api_key=${Constants.API_KEY}&language=en-US&page=1")
    fun getPopularMovies(): Call<MovieResponse>

}
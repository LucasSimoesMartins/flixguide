package com.lucassimoesmartins.flixguide.network.webclient

import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.RetrofitConfig
import com.lucassimoesmartins.flixguide.network.service.Service

class WebClient(
    private val service: Service = RetrofitConfig().service
) {

    suspend fun getPopularMovieList(): MovieResponse {
        return service.getPopularMovieList()
    }

    suspend fun getTopRatedMovieList(): MovieResponse {
        return service.getTopRatedMovieList()
    }

    suspend fun getTopTenTodayMovieList(): MovieResponse {
        return service.getTopTenTodayMovieList()
    }

    suspend fun getNowPlayingMovieList(): MovieResponse {
        return service.getNowPlayingMovieList()
    }

    suspend fun getUpcomingMovieList(): MovieResponse {
        return service.getUpcomingMovieList()
    }

    suspend fun getOldMovieList(): MovieResponse {
        return service.getOldMovieList()
    }

}
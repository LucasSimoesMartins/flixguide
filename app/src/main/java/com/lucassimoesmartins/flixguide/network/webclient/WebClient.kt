package com.lucassimoesmartins.flixguide.network.webclient

import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.RetrofitConfig
import com.lucassimoesmartins.flixguide.network.service.Service

class WebClient(
    private val service: Service = RetrofitConfig().service
) {

    fun getPopularMovies(): MovieResponse? {
        return service.getPopularMovies().execute().body()
    }

}
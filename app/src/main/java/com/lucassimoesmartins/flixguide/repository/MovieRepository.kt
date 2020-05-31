package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.webclient.WebClient

class MovieRepository(
    private val webClient: WebClient
) {

    fun getPopularMovies(): LiveData<Resource<MovieResponse>> {
        val mutableLiveData = MutableLiveData<Resource<MovieResponse>>()

        webClient.getPopularMovies(success = { movieResponse ->
            movieResponse?.let {
                mutableLiveData.value = Resource(data = it)
            }
        }, failure = { errorMessage ->
            mutableLiveData.value = Resource(data = null, error = errorMessage)
        })

        return mutableLiveData
    }
}
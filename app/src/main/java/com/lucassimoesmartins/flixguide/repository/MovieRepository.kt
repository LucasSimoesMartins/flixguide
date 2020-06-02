package com.lucassimoesmartins.flixguide.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucassimoesmartins.flixguide.constant.Constants
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.webclient.WebClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieRepository(
    private val webClient: WebClient
) {

    fun getPopularMovies(
        job: Job
    ): LiveData<Resource<MovieResponse>> {
        return MutableLiveData<Resource<MovieResponse>>().also { mutableLiveData ->
            CoroutineScope(IO + job).launch {

                val resource: Resource<MovieResponse>? = try {
                    webClient.getPopularMovies()?.let { movieResponse ->
                        Resource(data = movieResponse)
                    } ?: run {
                        Resource(data = null, error = Constants.GENERIC_FAIL_MESSAGE) as Resource<MovieResponse>
                    }
                } catch (e: Exception) {
                    Resource(data = null, error = Constants.GENERIC_FAIL_MESSAGE)
                }
                mutableLiveData.postValue(resource)
            }
        }
    }
}
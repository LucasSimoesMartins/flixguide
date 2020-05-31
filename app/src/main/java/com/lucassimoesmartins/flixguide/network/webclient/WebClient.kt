package com.lucassimoesmartins.flixguide.network.webclient

import com.lucassimoesmartins.flixguide.constant.Constants
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.network.RetrofitConfig
import com.lucassimoesmartins.flixguide.network.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebClient(
    private val service: Service = RetrofitConfig().service
) {

    private fun <T> sendRequest(
        call: Call<T>,
        success: (response: T?) -> Unit,
        failure: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(
                call: Call<T>, response: Response<T>
            ) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(Constants.GENERIC_FAIL_MESSAGE)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                failure(t.message)
            }
        })
    }

    fun getPopularMovies(
        success: (response: MovieResponse?) -> Unit,
        failure: (error: String?) -> Unit
    ) {
        sendRequest(
            service.getPopularMovies(),
            success,
            failure
        )
    }

}
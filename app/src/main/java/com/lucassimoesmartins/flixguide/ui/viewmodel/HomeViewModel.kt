package com.lucassimoesmartins.flixguide.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.repository.MovieRepository
import com.lucassimoesmartins.flixguide.repository.Resource
import kotlinx.coroutines.Job

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val job: Job = Job()

    fun getPopularMovies(): LiveData<Resource<MovieResponse>> = repository.getPopularMovies(job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
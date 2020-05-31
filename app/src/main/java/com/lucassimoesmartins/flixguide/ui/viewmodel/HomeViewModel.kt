package com.lucassimoesmartins.flixguide.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucassimoesmartins.flixguide.model.MovieResponse
import com.lucassimoesmartins.flixguide.repository.MovieRepository
import com.lucassimoesmartins.flixguide.repository.Resource

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    fun getPopularMovies(): LiveData<Resource<MovieResponse>> = repository.getPopularMovies()

}
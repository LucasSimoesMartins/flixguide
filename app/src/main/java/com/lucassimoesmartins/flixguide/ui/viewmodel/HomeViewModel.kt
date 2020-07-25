package com.lucassimoesmartins.flixguide.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucassimoesmartins.flixguide.predefined.CategoryEnum
import com.lucassimoesmartins.flixguide.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val imgFeaturedMovie = repository.imgFeaturedMovie
    fun getImgMovieList(categoryEnum: CategoryEnum) = repository.getImgMovieList(categoryEnum)

    fun getMovies() {
        launchDataLoad {
            repository.getMovies()
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
//                spinner.value = true
                block()
            } catch (error: Exception) {
//                snackBar.value = error.message
            } finally {
//                spinner.value = false
            }
        }
    }
}
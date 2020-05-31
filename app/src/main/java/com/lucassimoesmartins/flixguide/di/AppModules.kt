package com.lucassimoesmartins.flixguide.di

import com.lucassimoesmartins.flixguide.network.webclient.WebClient
import com.lucassimoesmartins.flixguide.repository.MovieRepository
import com.lucassimoesmartins.flixguide.ui.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<WebClient> {
        WebClient()
    }
    single<MovieRepository> {
        MovieRepository(get())
    }
    viewModel<HomeViewModel> {
        HomeViewModel(get())
    }
}
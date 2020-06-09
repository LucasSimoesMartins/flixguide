package com.lucassimoesmartins.flixguide.di

import androidx.room.Room
import com.lucassimoesmartins.flixguide.database.AppDatabase
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.network.webclient.WebClient
import com.lucassimoesmartins.flixguide.repository.MovieRepository
import com.lucassimoesmartins.flixguide.ui.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATABASE_NAME = "flixguide.db"

val viewModelModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(get())
    }
}

val webClientModule = module {
    single<WebClient> {
        WebClient()
    }
}

val dbModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}

val daoModule = module {
    single<MovieDao> {
        get<AppDatabase>().dao
    }
}

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepository(get(), get())
    }
}

val appModules = listOf(
    viewModelModule,
    webClientModule,
    dbModule,
    daoModule,
    repositoryModule
)
package com.lucassimoesmartins.flixguide.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucassimoesmartins.flixguide.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @get:Query("select * from Movie order by id desc")
    val getMovies: LiveData<List<Movie>>

}
package com.lucassimoesmartins.flixguide.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.lucassimoesmartins.flixguide.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie ORDER BY id DESC")
    fun getMovies() : LiveData<List<Movie>>

}
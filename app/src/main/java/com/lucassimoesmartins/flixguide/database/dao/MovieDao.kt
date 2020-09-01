package com.lucassimoesmartins.flixguide.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucassimoesmartins.flixguide.model.Movie
import com.lucassimoesmartins.flixguide.model.MovieCategoryCrossRef

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @get:Query("select backdrop_path from Movie order by popularity desc")
    val getImgFeaturedMovie: LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCategoryCrossRefList(movieCategoryCrossRef: List<MovieCategoryCrossRef>)

    @Query("select poster_path from Movie m inner join MovieCategoryCrossRef c on m.id = c.movieId where c.categoryId = :categoryId and poster_path is not null order by popularity desc limit 20")
    fun getImgMovieList(categoryId: Int): LiveData<List<String>>

    @Query("delete from Movie")
    suspend fun deleteAllMovies()

}
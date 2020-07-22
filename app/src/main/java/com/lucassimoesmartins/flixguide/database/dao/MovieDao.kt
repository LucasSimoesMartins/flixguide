package com.lucassimoesmartins.flixguide.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lucassimoesmartins.flixguide.model.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @get:Query("select backdrop_path from Movie order by popularity desc")
    val getImgFeaturedMovie: LiveData<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoryList(categoryList: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCategoryCrossRefList(movieCategoryCrossRef: List<MovieCategoryCrossRef>)

    @Transaction
    @Query("SELECT * FROM Movie m inner join MovieCategoryCrossRef c on m.id = c.movieId where c.categoryId = :categoryId")
    fun getImgMovieList(categoryId: Int): LiveData<List<MovieWithCategoryList>>

}
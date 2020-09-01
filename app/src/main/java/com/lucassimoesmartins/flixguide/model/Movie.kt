package com.lucassimoesmartins.flixguide.model

import androidx.room.*

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

@Entity
data class Category(
    @PrimaryKey
    val id: Int,
    val description: String
)

@Entity(primaryKeys = ["movieId", "categoryId"])
data class MovieCategoryCrossRef(
    val movieId: Int,
    val categoryId: Int
)

data class MovieWithCategoryList(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "id",
        entity = Category::class,
        entityColumn = "id",
        associateBy = Junction(
            value = MovieCategoryCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "categoryId"
        )
    )
    val categoryList: List<Category>
)
package com.lucassimoesmartins.flixguide.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucassimoesmartins.flixguide.database.converter.Converters
import com.lucassimoesmartins.flixguide.database.dao.MovieDao
import com.lucassimoesmartins.flixguide.model.Category
import com.lucassimoesmartins.flixguide.model.Movie
import com.lucassimoesmartins.flixguide.model.MovieCategoryCrossRef

const val DATABASE_VERSION = 1

@Database(
    entities = [Movie::class, Category::class, MovieCategoryCrossRef::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: MovieDao
}
package com.lucassimoesmartins.flixguide.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lucassimoesmartins.flixguide.predefined.MovieCategory

class Converters {

    @TypeConverter
    fun jsonToIntList(json: String): List<Int> {
        return Gson().fromJson(json, Array<Int>::class.java).toList()
    }

    @TypeConverter
    fun intListToJson(intList: List<Int>): String {
        return Gson().toJson(intList)
    }

    @TypeConverter
    fun jsonToMovieCategory(json: String): MovieCategory {
        return Gson().fromJson(json, MovieCategory::class.java)
    }

    @TypeConverter
    fun movieCategoryToJson(movieCategory: MovieCategory): String {
        return Gson().toJson(movieCategory)
    }

}
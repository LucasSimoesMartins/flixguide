package com.lucassimoesmartins.flixguide.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun jsonToIntList(json: String): List<Int> {
        return Gson().fromJson(json, Array<Int>::class.java).toList()
    }

    @TypeConverter
    fun intListToJson(intList: List<Int>): String {
        return Gson().toJson(intList)
    }

}
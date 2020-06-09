package com.lucassimoesmartins.flixguide.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun jsonToIntList(json: String): List<Int> {
        return Gson().fromJson<List<Int>>(json, Int::class.java)
    }

    @TypeConverter
    fun intListToJson(intList: List<Int>): String {
        return Gson().toJson(intList)
    }

}
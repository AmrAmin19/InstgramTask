package com.example.instgramtask.Model.LocalData

import androidx.room.TypeConverter
import com.example.instgramtask.Model.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MediaConverter {

    @TypeConverter
    fun fromMediaList(media: List<Media>): String {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun toMediaList(value: String): List<Media> {
        val type = object : TypeToken<List<Media>>() {}.type
        return Gson().fromJson(value, type)
    }
}
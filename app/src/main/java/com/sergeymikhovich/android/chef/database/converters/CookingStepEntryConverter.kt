package com.sergeymikhovich.android.chef.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sergeymikhovich.android.chef.model.CookingStepEntry
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

object CookingStepEntryConverter {

    private lateinit var gson: Gson

    fun initializeGson(gson: Gson) {
        this.gson = gson
    }

    @TypeConverter
    fun fromEntries(list: List<CookingStepEntry>): String = gson.toJson(list)

    @TypeConverter
    fun toEntries(json: String): List<CookingStepEntry> {
        val listType = object : TypeToken<List<CookingStepEntry>>() {}.type

        return try {
            gson.fromJson(json, listType)
        } catch (error: Throwable) {
            emptyList()
        }
    }
}
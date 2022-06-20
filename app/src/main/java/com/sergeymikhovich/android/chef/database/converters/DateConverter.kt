package com.sergeymikhovich.android.chef.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long?): Date = Date(timestamp ?: 0)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long = date?.time ?: 0
}
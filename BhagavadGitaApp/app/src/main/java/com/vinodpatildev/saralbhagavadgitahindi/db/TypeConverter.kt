package com.vinodpatildev.saralbhagavadgitahindi.db

import androidx.room.TypeConverter
import java.util.Date

class TypeConverter {
    @TypeConverter
    fun fromDate(date : Date) :Long {
        return date.time
    }
    @TypeConverter
    fun toDate(milliSinceEpoch : Long) : Date {
        return Date(milliSinceEpoch)
    }
}
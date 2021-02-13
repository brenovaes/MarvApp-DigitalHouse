package com.digitalhouse.br.marvelapp

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Suppress("DEPRECATION")
class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }


    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString()
    }
}

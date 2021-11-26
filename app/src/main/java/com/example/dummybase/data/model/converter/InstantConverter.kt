package com.example.dummybase.data.model.converter

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant

class InstantConverter {
    @FromJson
    @TypeConverter
    fun fromString(value: String?): Instant? = value?.let { Instant.parse(it) }

    @ToJson
    @TypeConverter
    fun dateToString(date: Instant?): String? = date?.toString()
}
package com.example.dummybase.data.model.converter

import androidx.room.TypeConverter
import com.example.dummybase.data.model.User
import com.example.dummybase.di.ApiModule

open class ModelConverter<T>(type: Class<T>) {

    private val jsonAdapter = ApiModule.moshi.adapter(type)

    @TypeConverter
    fun fromString(value: String?): T? = value?.let { jsonAdapter.fromJson(value) }

    @TypeConverter
    fun fromType(type: T?): String? = type?.let { jsonAdapter.toJson(type) }
}

class UserConverter : ModelConverter<User>(User::class.java)

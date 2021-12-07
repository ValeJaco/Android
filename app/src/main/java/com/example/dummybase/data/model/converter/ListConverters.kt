package com.example.dummybase.data.model.converter

import androidx.room.TypeConverter
import com.example.dummybase.data.model.User
import com.example.dummybase.di.ApiModule
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types

class ListConverters {

    val moshi = ApiModule.moshi

    /*@TypeConverter
    fun fromUserString(value: String?): List<User>? = value?.let {
        val listType = Types.newParameterizedType(List::class.java, User::class.java)
        val adapter: JsonAdapter<List<User>> = moshi.adapter(listType)
        adapter.fromJson(it)
    }

    @TypeConverter
    fun fromUsers(list: List<User>?): String? = list?.let {
        val listType = Types.newParameterizedType(List::class.java, User::class.java)
        val adapter: JsonAdapter<List<User>> = moshi.adapter(listType)
        adapter.toJson(it)
    }*/

}
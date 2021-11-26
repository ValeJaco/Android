package com.example.dummybase.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dummybase.data.UserDao
import com.example.dummybase.data.model.converter.ListConverters
import com.example.dummybase.data.model.converter.UserConverter
import com.example.dummybase.data.model.User

@Database(
    entities = [User::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    value = [
        UserConverter::class,
        // ListConverters::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}

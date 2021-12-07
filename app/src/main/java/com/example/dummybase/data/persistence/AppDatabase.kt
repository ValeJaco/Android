package com.example.dummybase.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dummybase.data.model.Seance
import com.example.dummybase.data.model.User
import com.example.dummybase.data.model.converter.InstantConverter

@Database(
    entities = [User::class, Seance::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    value = [
        // UsersConverter::class,
        // SeancesConverter::class
        // ListConverters::class
        InstantConverter:: class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun seancesDao(): SeancesDao
}

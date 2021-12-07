package com.example.dummybase.di

import android.app.Application
import androidx.room.Room
import com.example.dummybase.data.persistence.UsersDao
import com.example.dummybase.data.persistence.AppDatabase
import com.example.dummybase.data.persistence.SeancesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase = Room
        .databaseBuilder(application, AppDatabase::class.java, "Crossfit.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideUsersDao(appDatabase: AppDatabase): UsersDao = appDatabase.usersDao()

    @Provides
    @Singleton
    fun provideSeancesDao(appDatabase: AppDatabase): SeancesDao = appDatabase.seancesDao()
}
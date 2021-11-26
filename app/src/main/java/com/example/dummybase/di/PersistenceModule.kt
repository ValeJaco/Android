package com.example.dummybase.di

import android.app.Application
import androidx.room.Room
import com.example.dummybase.data.UserDao
import com.example.dummybase.data.persistence.AppDatabase
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
        .databaseBuilder(application, AppDatabase::class.java, "Cuverie.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideAbilityDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

}
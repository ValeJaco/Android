package com.example.dummybase.di

import com.example.dummybase.api.ApiClient
import com.example.dummybase.data.persistence.UsersDao
import com.example.dummybase.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        apiClient: ApiClient,
        usersDao: UsersDao
    ): UserRepository = UserRepository(apiClient, usersDao)

}
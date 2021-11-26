package com.example.dummybase.di

import com.example.dummybase.api.ApiClient
import com.example.dummybase.data.UserDao
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
        userDao: UserDao
    ): UserRepository = UserRepository(apiClient, userDao)

}
package com.example.dummybase.data.repository

import com.example.dummybase.api.ApiClient
import com.example.dummybase.data.persistence.UsersDao
import com.example.dummybase.data.model.User
import com.example.dummybase.utils.resultLiveData
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val usersDao: UsersDao
) : Repository {

    fun observeUserList() = resultLiveData(
        databaseQuery = { usersDao.getUserList() },
        networkCall = { apiClient.fetchUserList() },
        saveCallResult = { usersDao.insertUserList( it ) }
    )

    fun getUserById( userId: Int) = resultLiveData <User, User>(
        databaseQuery = { usersDao.getUserById(userId) }
    )
}

package com.example.dummybase.data.repository

import com.example.dummybase.api.ApiClient
import com.example.dummybase.data.UserDao
import com.example.dummybase.data.model.User
import com.example.dummybase.utils.resultLiveData
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val userDao: UserDao
) : Repository {

    fun observeUserList() = resultLiveData(
        databaseQuery = { userDao.getUserList() },
        networkCall = { apiClient.fetchUserList() },
        saveCallResult = { userDao.insertUserList( it ) }
    )

    /*fun callUserList() = resultLiveData<List<User>, List<User>>(
        networkCall = { apiClient.fetchUserList() },
        saveCallResult = { userDao.insertUserList( it ) }
    )

    fun getUserList() = resultLiveData<List<User>, User>(
        databaseQuery = { userDao.getUserList() }
    )*/
}

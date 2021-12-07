package com.example.dummybase.api

import com.example.dummybase.base.BaseDataSource
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun fetchUserList()  = getResult {
        apiService.fetchUserList()
    }

    suspend fun fetchUserById(id: Int) = getResult {
        apiService.fetchUserbyId(id)
    }

    suspend fun fetchSeances()  = getResult {
        apiService.fetchSeances()
    }

    suspend fun fetchSeanceById(id: Int) = getResult {
        apiService.fetchSeancebyId( id )
    }

    suspend fun logIn(login: String, password: String ) = getResult {
        apiService.logIn(login, password)
    }
}

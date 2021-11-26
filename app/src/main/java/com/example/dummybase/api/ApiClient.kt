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
}

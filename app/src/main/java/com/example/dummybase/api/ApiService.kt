package com.example.dummybase.api

import com.example.dummybase.data.model.AuthResponseHolder
import com.example.dummybase.data.model.Seance
import com.example.dummybase.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("api/getUser/{id}")
    suspend fun fetchUserbyId(@Path("id") id: Int): Response<User>

    @GET("api/getUsers")
    suspend fun fetchUserList(): Response<List<User>>

    @GET("api/getSeance/{id}")
    suspend fun fetchSeancebyId(@Path("id") id: Int): Response<Seance>

    @GET("api/seancesList")
    suspend fun fetchSeances(): Response<List<Seance>>

    @POST( "api/login" )
    suspend fun logIn( login: String, password: String ): Response<AuthResponseHolder>

}
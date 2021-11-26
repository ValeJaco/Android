package com.example.dummybase.api

import com.example.dummybase.data.model.User
import retrofit2.Response
import retrofit2.http.*

const val extra = ""

interface ApiService {

    @GET("api/router/comptes/{id}$extra")
    suspend fun fetchUserbyId(@Path("id") id: Int): Response<User>

    @GET("apiRouter/comptes")
    suspend fun fetchUserList(): Response<List<User>>

    // @GET("pokemon$extra")
    // suspend fun fetchUserList(@Query("limit") limit: Int?, @Query("offset") offset: Int?): Response<PokemonResponse>


}
package com.solar.retrofit.service

import com.solar.retrofit.response.GetUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("search/users")
    suspend fun getUserList(
        @Query("q", encoded = true) query: String
    ): GetUserResponse
}
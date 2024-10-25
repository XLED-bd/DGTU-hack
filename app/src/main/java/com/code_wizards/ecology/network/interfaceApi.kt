package com.code_wizards.ecology.network

import com.code_wizards.ecology.models.User
import retrofit2.http.GET
import retrofit2.http.Path


interface interfaceApi {
    @GET("get_user/{id}")
    suspend fun getPrediction(@Path("id") postId: Int): User
}
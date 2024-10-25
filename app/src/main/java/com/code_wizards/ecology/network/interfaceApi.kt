package com.code_wizards.ecology.network

import com.code_wizards.ecology.models.Purchase
import com.code_wizards.ecology.models.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface interfaceApi {
    @GET("get_user/{id}")
    suspend fun getPrediction(@Path("id") postId: Int): User

    @POST("user/login")
    suspend fun login(email: String, password: String): Int

    @POST("user/register")
    suspend fun registration(email: String, password: String): Int

    @POST("user/list_purchases")
    suspend fun getPurchasesByUser(id_user: Int): List<Purchase>
}
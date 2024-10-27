package com.code_wizards.ecology.network

import com.code_wizards.ecology.models.LoginRequest
import com.code_wizards.ecology.models.Purchaser
import com.code_wizards.ecology.models.Receipt
import com.code_wizards.ecology.models.RegisterRequst
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.models.Userback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface interfaceApi {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") postId: Int): Userback

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Int

    @POST("auth/register")
    suspend fun registration(@Body registerRequst: RegisterRequst)
}

interface interfaceCompanyApi {
    @GET("purchasers")
    suspend fun getPurchaser(
        @Query("email") email: String? = null,
        @Query("phone_number") phoneNumber: String? = null
    ): Purchaser


    @POST("purchasers/{purchaserId}/grantAccess")
    suspend fun requestAccessCode(
        @Path("purchaserId") purchaserId: String
    ): Response<Unit>


    @POST("purchasers/{purchaserId}/grantAccess/{code}")
    suspend fun verifyAccessCode(
        @Path("purchaserId") purchaserId: String,
        @Path("code") code: String
    ): Response<Unit>


    @GET("purchasers/{purchaserId}/receipts")
    suspend fun getReceipts(
        @Path("purchaserId") purchaserId: String,
    ): List<Receipt>
}
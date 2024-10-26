package com.code_wizards.ecology.repository

import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.network.interfaceApi

class UserRepository(private val api: interfaceApi) {
    suspend fun login(email:String, password: String): Result<Int>  = try {
        Result.success(api.login(email, password))
    } catch (e: Exception){
        Result.failure(e)
    }

    suspend fun registration(email:String, password: String): Result<Int>  = try {
        Result.success(api.registration(email, password))
    } catch (e: Exception){
        Result.failure(e)
    }
}
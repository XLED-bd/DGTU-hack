package com.code_wizards.ecology.repository

import com.code_wizards.ecology.models.LoginRequest
import com.code_wizards.ecology.models.RegisterRequst
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.network.interfaceApi

class UserRepository(private val api: interfaceApi) {
    suspend fun login(loginRequest: LoginRequest): Result<Int> = try {
        Result.success(api.login(loginRequest))
    } catch (e: Exception){
        Result.failure(e)
    }

    suspend fun registration(registerRequst: RegisterRequst): Result<Int> {
        try {
            Result.success(api.registration(registerRequst))
            return Result.success(1)
        } catch (e: Exception){
            return Result.failure(e)
        }
    }
}
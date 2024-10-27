package com.code_wizards.ecology.repository

import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.models.Userback
import com.code_wizards.ecology.network.interfaceApi

class ProfileRepository(private val api: interfaceApi) {
    suspend fun getUser(id: Int): Result<Userback> = try {
        Result.success(api.getUser(id))
    } catch (e: Exception){
        Result.failure(e)
    }
}
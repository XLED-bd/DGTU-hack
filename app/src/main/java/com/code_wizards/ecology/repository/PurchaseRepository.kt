package com.code_wizards.ecology.repository

import com.code_wizards.ecology.models.Purchase
import com.code_wizards.ecology.network.interfaceApi

class PurchaseRepository(private val api: interfaceApi) {
    suspend fun getPurchasesByUser(id: Int): Result<List<Purchase>> = try {
        Result.success(api.getPurchasesByUser(id))
    } catch (e: Exception){
        Result.failure(e)
    }
}
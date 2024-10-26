package com.code_wizards.ecology.repository

import com.code_wizards.ecology.models.Purchaser
import com.code_wizards.ecology.models.Receipt
import com.code_wizards.ecology.network.interfaceApi
import com.code_wizards.ecology.network.interfaceCompanyApi
import retrofit2.HttpException

class PurchaseRepository(private val api: interfaceCompanyApi) {
    suspend fun getPurchaser(email: String? = null, phoneNumber: String? = null): Result<Purchaser> =
        runCatching {
            api.getPurchaser(email, phoneNumber)
        }

    suspend fun requestAccessCode(purchaserId: String): Result<Unit> =
        runCatching {
            val response = api.requestAccessCode(purchaserId)
            if (response.isSuccessful) Unit
            else throw HttpException(response)
        }

    suspend fun verifyAccessCode(purchaserId: String, code: String): Result<Unit> =
        runCatching {
            val response = api.verifyAccessCode(purchaserId, code)
            if (response.isSuccessful) Unit
            else throw HttpException(response)
        }

    suspend fun getReceipts(
        purchaserId: String
    ): Result<List<Receipt>> =
        runCatching {
            api.getReceipts(purchaserId)
        }
}
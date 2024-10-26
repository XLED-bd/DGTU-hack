package com.code_wizards.ecology.models


data class Purchase(
    val productName: String,
    val price: String,
    val carbonImpact: String,
    val ecoFriendly: Boolean
)



data class ReceiptItem(
    val name: String,
    val count: Double,
    val price: Double
)

data class Receipt(
    val id: String,
    val time: String,
    val items: List<ReceiptItem>,
    val totalPrice: Double,
    val category: String
)

data class Purchaser(
    val id: String,
    val access: Boolean
)

sealed class PurchaserState {
    object Initial : PurchaserState()
    object Loading : PurchaserState()
    data class Success(val purchaser: Purchaser) : PurchaserState()
    data class Error(val message: String) : PurchaserState()
}

sealed class AccessCodeState {
    object Initial : AccessCodeState()
    object Loading : AccessCodeState()
    object CodeSent : AccessCodeState()
    object Verified : AccessCodeState()
    data class Error(val message: String) : AccessCodeState()
}

sealed class ReceiptsState {
    object Initial : ReceiptsState()
    object Loading : ReceiptsState()
    data class Success(val receipts: List<Receipt>) : ReceiptsState()
    data class Error(val message: String) : ReceiptsState()
}
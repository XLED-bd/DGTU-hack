package com.code_wizards.ecology.models

data class Purchase(
    val productName: String,
    val price: String,
    val carbonImpact: String,
    val ecoFriendly: Boolean
)
package com.code_wizards.ecology.models

data class User(
    val id: Int? = null,
    val first_name: String,
    val second_name: String,
    val email: String,
    val phone: String,
    val carbonFootprint: String,
    val achievements: List<Achievement>,
    val dailyImpact: List<Float>
)

data class Userback(
    val id_user: String? = null,
    val name: String,
    val email: String,
    val city: String? = null

)

data class Achievement(val title: String, val description: String)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequst(
    val name: String,
    val email: String,
    val city: String,
    val password: String)
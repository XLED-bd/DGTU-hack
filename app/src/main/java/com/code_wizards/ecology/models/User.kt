package com.code_wizards.ecology.models

data class User(
    val id: Int,
    val first_name: String,
    val second_name: String,
    val carbonFootprint: String,
    val achievements: List<Achievement>,
    val dailyImpact: List<Float>
)

data class Achievement(val title: String, val description: String)
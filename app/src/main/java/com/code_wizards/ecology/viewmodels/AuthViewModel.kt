package com.code_wizards.ecology.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthViewModel(private val context: Context) : ViewModel() {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var isLoggedIn = mutableStateOf(false)

    init {
        isLoggedIn.value = sharedPrefs.getBoolean("isLoggedIn", false)
    }

    fun login() {
        viewModelScope.launch {
            if (username.value.isNotBlank() && password.value.isNotBlank()) {
                sharedPrefs.edit().putBoolean("isLoggedIn", true).apply()
                isLoggedIn.value = true
                errorMessage.value = "Login successful"
            } else {
                errorMessage.value = "All fields are required"
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            if (password.value == confirmPassword.value && username.value.isNotBlank()) {
                sharedPrefs.edit().putBoolean("isLoggedIn", true).apply()
                isLoggedIn.value = true
                errorMessage.value = "Registration successful"
            } else {
                errorMessage.value = "Registration failed"
            }
        }
    }

    fun logout() {
        sharedPrefs.edit().remove("isLoggedIn").apply()
        isLoggedIn.value = false
    }
}
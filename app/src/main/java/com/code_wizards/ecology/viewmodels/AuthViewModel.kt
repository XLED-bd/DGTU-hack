package com.code_wizards.ecology.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userRepository: UserRepository) : ViewModel() {

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    private val _isIDLoggedIn = MutableStateFlow(-1)
    val isIDLoggedIn = _isIDLoggedIn.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isIDLoggedIn.value = sharedPrefs.getInt("isLoggedIn", -1)
        }
    }

    fun login() {
        if (username.value.isNotBlank() && password.value.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                    sharedPrefs.edit().putInt("isLoggedIn", 1).apply() // userRepository.login()
                    _isIDLoggedIn.value = 0 // userRepository.login()
                }
            errorMessage.value = "Login successful"
            }
        else {
            errorMessage.value = "All fields are required"
        }
        }

    fun register() {
        if (username.value.isNotBlank() && password.value.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                sharedPrefs.edit().putInt("isLoggedIn", 1).apply() // userRepository.login()
                _isIDLoggedIn.value = 0 // userRepository.login()
            }
            errorMessage.value =  "Registration successful"
        }
        else {
            errorMessage.value = "Registration failed"
        }
    }

    fun logout() {
        sharedPrefs.edit().remove("isLoggedIn").apply()
        _isIDLoggedIn.value = -1
    }
}
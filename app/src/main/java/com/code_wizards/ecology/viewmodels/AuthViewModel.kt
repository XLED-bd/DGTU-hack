package com.code_wizards.ecology.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.models.LoginRequest
import com.code_wizards.ecology.models.ReceiptsState
import com.code_wizards.ecology.models.RegisterRequst
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
    var username_ = mutableStateOf("")

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
                userRepository.login(
                    LoginRequest(username.value, password.value))
                    .onSuccess { user ->
                        _isIDLoggedIn.value = user
                        errorMessage.value = user.toString()
                    }
                    .onFailure { error ->
                        errorMessage.value = error.toString()
                    }

                sharedPrefs.edit().putInt("isLoggedIn", _isIDLoggedIn.value).apply() // userRepository.login()

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
                userRepository.registration(registerRequst = RegisterRequst(username_.value, username.value,
                    "Ростов", password.value))
                    .onSuccess { id ->
                        _isIDLoggedIn.value = id
                    }
                    .onFailure { error ->
                        _isIDLoggedIn.value = -1
                        errorMessage.value = error.toString()
                    }

                //sharedPrefs.edit().putInt("isLoggedIn", 1).apply() // userRepository.login()
                _isIDLoggedIn.value = -1 // userRepository.login()
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
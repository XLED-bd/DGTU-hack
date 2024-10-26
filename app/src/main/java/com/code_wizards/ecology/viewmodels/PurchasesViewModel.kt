package com.code_wizards.ecology.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.models.AccessCodeState
import com.code_wizards.ecology.models.Purchaser
import com.code_wizards.ecology.models.PurchaserState
import com.code_wizards.ecology.repository.PurchaseRepository
import com.code_wizards.ecology.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PurchaserViewModel @Inject constructor(private val purchaseRepository: PurchaseRepository): ViewModel() {
    private val _purchaserState = MutableStateFlow<PurchaserState>(PurchaserState.Initial)
    val purchaserState = _purchaserState.asStateFlow()

    private val _accessCodeState = MutableStateFlow<AccessCodeState>(AccessCodeState.Initial)
    val accessCodeState = _accessCodeState.asStateFlow()


    fun searchPurchaser(email: String? = null, phoneNumber: String? = null) {
        viewModelScope.launch {
            _purchaserState.value = PurchaserState.Loading
            purchaseRepository.getPurchaser(email, phoneNumber)
                .onSuccess { purchaser ->
                    _purchaserState.value = PurchaserState.Success(purchaser)
                    Log.d("sP", _purchaserState.value.toString())
                }
                .onFailure { error ->
                    _purchaserState.value = PurchaserState.Error(error.message ?: "Unknown error")
                }
        }
    }

    fun requestAccessCode(purchaserId: String) {
        viewModelScope.launch {
            _accessCodeState.value = AccessCodeState.Loading
            purchaseRepository.requestAccessCode(purchaserId)
                .onSuccess {
                    _accessCodeState.value = AccessCodeState.CodeSent
                    Log.d("rAC", _accessCodeState.value.toString())
                }
                .onFailure { error ->
                    _accessCodeState.value = AccessCodeState.Error(error.message ?: "Unknown error")
                    Log.d("rAC", AccessCodeState.Error(error.message ?: "Unknown error").toString())
                }
        }
    }

    fun verifyAccessCode(purchaserId: String, code: String) {
        viewModelScope.launch {
            _accessCodeState.value = AccessCodeState.Loading
            purchaseRepository.verifyAccessCode(purchaserId, code)
                .onSuccess {
                    _accessCodeState.value = AccessCodeState.Verified
                    Log.d("vAC", _accessCodeState.value.toString())
                }
                .onFailure { error ->
                    _accessCodeState.value = AccessCodeState.Error(error.message ?: "Unknown error")
                }
        }
    }
}

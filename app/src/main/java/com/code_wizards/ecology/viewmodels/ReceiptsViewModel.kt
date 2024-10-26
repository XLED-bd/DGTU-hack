package com.code_wizards.ecology.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.models.ReceiptsState
import com.code_wizards.ecology.repository.PurchaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReceiptsViewModel @Inject constructor(
    private val repository: PurchaseRepository
) : ViewModel() {
    private val _receiptsState = MutableStateFlow<ReceiptsState>(ReceiptsState.Initial)
    val receiptsState = _receiptsState.asStateFlow()

    fun loadReceipts(purchaserId: String) {
        viewModelScope.launch {
            _receiptsState.value = ReceiptsState.Loading
            repository.getReceipts(purchaserId)
                .onSuccess { receipts ->
                    _receiptsState.value = ReceiptsState.Success(receipts)
                }
                .onFailure { error ->
                    _receiptsState.value = ReceiptsState.Error(error.message ?: "Unknown error")
                }
        }
    }
}
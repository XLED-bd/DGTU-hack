package com.code_wizards.ecology.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.models.Purchase
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
class PurchasesViewModel @Inject constructor(val purchaseRepository: PurchaseRepository): ViewModel() {
    private val _purchases = MutableStateFlow<List<Purchase>>(emptyList())
    val purchases: StateFlow<List<Purchase>> = _purchases.asStateFlow()

    fun loadPurchases(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            //purchaseRepository.getPurchasesByUser(id).onSuccess { _purchases.value = it }
            _purchases.value = listOf(
                Purchase("Reusable Water Bottle", "$25.99", "Reduced by 10kg", ecoFriendly = true),
                Purchase("Organic Cotton Bag", "$15.49", "Reduced by 5kg", ecoFriendly = true),
                Purchase("Electric Scooter Rental", "$8.00", "Reduced by 3kg", ecoFriendly = true),
                Purchase("Single-use Plastic Bag", "$0.10", "Increased by 0.1kg", ecoFriendly = false)
            )
        }
    }
}
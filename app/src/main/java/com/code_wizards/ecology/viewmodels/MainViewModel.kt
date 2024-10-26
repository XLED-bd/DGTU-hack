package com.code_wizards.ecology.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.models.Achievement
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.repository.ProfileRepository
import com.code_wizards.ecology.ui.bottonbar.BottomNavItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel@Inject constructor(profileRepository: ProfileRepository): ViewModel() {
    private val _selectedItem = MutableStateFlow<BottomNavItem>(BottomNavItem.Home)
    val selectedItem: StateFlow<BottomNavItem> = _selectedItem.asStateFlow()

    private val _user = MutableStateFlow<User>(User(
            1, "Иван", "Иванов", "user1@example.com", "79001234567", "150",
            listOf(
                Achievement("Eco Saver", "Reduced 100kg of carbon"),
                Achievement("Green Thumb", "Planted 10 trees"),
                Achievement("Plastic Warrior", "Avoided 50 plastic bags")
            ),
            emptyList()
        ))
    val user = _user.asStateFlow()

    fun onItemSelected(item: BottomNavItem) {
        _selectedItem.value = item
    }

    fun loadUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            //profileRepository.getUser(id).onSuccess { _user.value = it }

            _user.value = User(
                1, "Иван", "Иванов", "user1@example.com", "79001234567", "150",
                listOf(
                    Achievement("Eco Saver", "Reduced 100kg of carbon"),
                    Achievement("Green Thumb", "Planted 10 trees"),
                    Achievement("Plastic Warrior", "Avoided 50 plastic bags")
                ),
                emptyList()
            )
        }
    }



}
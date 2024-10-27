package com.code_wizards.ecology.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code_wizards.ecology.models.Achievement
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.models.Userback
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
class MainViewModel@Inject constructor(private val profileRepository: ProfileRepository): ViewModel() {
    private val _selectedItem = MutableStateFlow<BottomNavItem>(BottomNavItem.Home)
    val selectedItem: StateFlow<BottomNavItem> = _selectedItem.asStateFlow()

    private val _user = MutableStateFlow<User>(User(
            1, "Иван", "Иванов", "user1@example.com", "79001234567", "150",
            listOf(
                Achievement("Хранитель природы", "За неделю иметь меньше 20 кг углеродного следа"),
                Achievement("Пользователь автобуса", "Куплен 10 проезд на втобусе"),
                Achievement("Plastic Warrior", "Avoided 50 plastic bags")
            ),
            emptyList()
        ))
    val user = _user.asStateFlow()

    val _userback = MutableStateFlow<Userback>(Userback("0", "0", "1", "1"))
    val userback = _userback.asStateFlow()

    fun onItemSelected(item: BottomNavItem) {
        _selectedItem.value = item
    }

    fun loadUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getUser(id).onSuccess { _userback.value = it }
                .onFailure { _userback.value = Userback(name = "ERORR", email = "1") }
            Log.d("1111", _userback.value.toString())
            _user.value = User(
                null, _userback.value.name, "", _userback.value.email, "79001234567", "150",
                listOf(
                    Achievement("Хранитель природы", "За неделю иметь меньше 20 кг углеродного следа"),
                    Achievement("Король автобусов", "Куплено 100 проездов на автобуче"),
                    Achievement("Пользователь автобуса", "Куплен 10 проездов на автобусе"),

                ),
                emptyList()
            )
        }
    }



}
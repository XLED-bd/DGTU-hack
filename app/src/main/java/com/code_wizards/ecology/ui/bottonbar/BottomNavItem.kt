package com.code_wizards.ecology.ui.bottonbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.code_wizards.ecology.R
import com.code_wizards.ecology.navigation.Screen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.MainPage.route,
        title = "Главная",
        icon = Icons.Default.Home
    )
    object Search : BottomNavItem(
        route = Screen.MapPage.route,
        title = "Карта",
        icon = Icons.Default.Place
    )
    object Camera: BottomNavItem(
        route = "cameraPage", //????????????????????????
        title = "Камера",
        icon = Icons.Default.PlayArrow
    )
    object Profile : BottomNavItem(
        route = Screen.ProfilePage.route,
        title = "Профиль",
        icon = Icons.Default.Person
    )
    object Purchases : BottomNavItem(
        route = Screen.VerifyPage.route,
        title = "Покупки",
        icon = Icons.Default.ShoppingCart
    )

    companion object {
        fun getItems() = listOf(Home, Search, Camera, Purchases, Profile)
    }
}
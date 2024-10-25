package com.code_wizards.ecology.ui.mainpage.bottonbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        title = "Главная",
        icon = Icons.Default.Home
    )
    object Search : BottomNavItem(
        route = "search",
        title = "Поиск",
        icon = Icons.Default.Search
    )
    object Favorite : BottomNavItem(
        route = "favorite",
        title = "Избранное",
        icon = Icons.Default.Favorite
    )
    object Profile : BottomNavItem(
        route = "profile",
        title = "Профиль",
        icon = Icons.Default.Person
    )
    object Settings : BottomNavItem(
        route = "settings",
        title = "Настройки",
        icon = Icons.Default.Settings
    )
}
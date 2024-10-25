package com.code_wizards.ecology.ui.bottonbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.code_wizards.ecology.viewmodels.MainViewModel

@Composable
fun BottomNavigationBar(navController: NavController, viewModel: MainViewModel) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favorite,
        BottomNavItem.Purchases,
        BottomNavItem.Profile
    )
    val selectedItem by viewModel.selectedItem.collectAsState()

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = selectedItem == item,
                onClick = {
                    viewModel.onItemSelected(item)
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
// Аналогично для Cart и Profile


fun BottomNavigationItem(){

}


package com.code_wizards.ecology.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.MainPage


@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Screen.MainPage.route
    ) {
        val viewModel: ViewModel
        composable(Screen.MainPage.route){
            MainPage(modifier, navController)
        }

//        composable(Screen.SecondPage.route){
//
//        }

    }
}
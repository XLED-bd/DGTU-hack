package com.code_wizards.ecology.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.MainPage


@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    //val viewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route){
            MainPage(navController)
        }

//        composable(Screen.SecondPage.route){
//
//        }

    }
}
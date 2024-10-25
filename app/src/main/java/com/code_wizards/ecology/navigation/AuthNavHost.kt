package com.code_wizards.ecology.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.MainPage
import com.code_wizards.ecology.ui.auth.LoginScreen
import com.code_wizards.ecology.ui.auth.RegisterScreen
import com.code_wizards.ecology.viewmodels.AuthViewModel

@Composable
fun AuthNavHost(viewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable("register") {
            RegisterScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.MainPage.route) {
            NavigationApp()
        }
    }
}

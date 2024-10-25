package com.code_wizards.ecology.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.auth.LoginScreen
import com.code_wizards.ecology.ui.auth.RegisterScreen
import com.code_wizards.ecology.ui.mainpage.MainPage
import com.code_wizards.ecology.ui.mappage.MapPage
import com.code_wizards.ecology.viewmodels.AuthViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AuthNavHost() {
    val navController = rememberNavController()

    val viewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable("register") {
            RegisterScreen(viewModel = viewModel, navController = navController)
        }


        composable(Screen.MainPage.route){
            MainPage(navController, viewModel.isIDLoggedIn.value)
        }


        composable(Screen.MapPage.route){
            MapPage(navController)
        }


//        composable(Screen.MainPage.route){
//            MainPage(navController, viewModel.isIDLoggedIn.value)
//        }
    }
}

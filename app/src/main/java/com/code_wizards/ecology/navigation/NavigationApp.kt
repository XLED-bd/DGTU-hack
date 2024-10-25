package com.code_wizards.ecology.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.mainpage.MainPage
import com.code_wizards.ecology.ui.mappage.MapPage
import com.code_wizards.ecology.ui.purchases.PurchasesPage
import com.code_wizards.ecology.viewmodels.AuthViewModel
import com.code_wizards.ecology.viewmodels.MainViewModel


@Composable
fun NavigationApp(id_user: Int) {

    val navController = rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController= navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route){
            MainPage(navController, id_user)
        }


        composable(Screen.MapPage.route){
            MapPage(navController, viewModel)
        }

        composable(Screen.PurchasesPage.route){
            PurchasesPage(navController, viewModel)
        }

//        composable(Screen.SecondPage.route){
//
//        }

    }
}
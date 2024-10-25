package com.code_wizards.ecology.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.mainpage.MainPage
import com.code_wizards.ecology.viewmodels.AuthViewModel

//
//@Composable
//fun NavigationApp(id_user: Int, navController: NavController) {
//
//    //val viewModel: MainViewModel = hiltViewModel()
//
//    NavHost(
//        navController= navController,
//        startDestination = Screen.MainPage.route
//    ) {
//        composable(Screen.MainPage.route){
//            MainPage(navController, id_user)
//        }
//
////        composable(Screen.SecondPage.route){
////
////        }
//
//    }
//}
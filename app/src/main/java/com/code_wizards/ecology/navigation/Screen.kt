package com.code_wizards.ecology.navigation

sealed class Screen(val route: String){
    object MainPage: Screen("mainPage")
}
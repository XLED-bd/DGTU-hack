package com.code_wizards.ecology.navigation

sealed class Screen(val route: String){
    object MainPage: Screen("mainPage")
    object MapPage: Screen("mapPage")
    object PurchasesPage: Screen("purchasesPage")
    object ProfilePage: Screen("profileOage")
    object CameraPage: Screen("cameraPage")
    object VerifyPage: Screen("verifyPage")
}

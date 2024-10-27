package com.code_wizards.ecology.navigation

sealed class Screen(val route: String){
    object MainPage: Screen("mainPage")
    object MapPage: Screen("mapPage")
    object PurchasesPage: Screen("purchasesPage")
    object ProfilePage: Screen("profileOage")
    object CameraPage: Screen("cameraPage")
    object VerifyPage: Screen("verifyPage")

    object Slide1Tip: Screen("slide1tip")
    object Slide2Tip: Screen("slide2tip")
    object Slide3Tip: Screen("slide3tip")
}

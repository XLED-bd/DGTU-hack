package com.code_wizards.ecology.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.R
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.ui.camera.CameraPage
import com.code_wizards.ecology.ui.mainpage.MainPage
import com.code_wizards.ecology.ui.mappage.MapPage
import com.code_wizards.ecology.ui.profilepage.ProfilePage
import com.code_wizards.ecology.ui.purchases.PurchasesPage
import com.code_wizards.ecology.ui.purchases.verifypage.VerifyAccessScreen
import com.code_wizards.ecology.ui.slides.EcoTipsPage
import com.code_wizards.ecology.viewmodels.AuthViewModel
import com.code_wizards.ecology.viewmodels.MainViewModel
import com.code_wizards.ecology.viewmodels.PurchaserViewModel


@Composable
fun NavigationApp(id_user: Int) {

    val navController = rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()

    viewModel.loadUser(id_user)

    val user: User by viewModel.user.collectAsState()

    val purchaserViewModel: PurchaserViewModel = hiltViewModel()
    purchaserViewModel.searchPurchaser(user.email, user.phone)


    NavHost(
        navController= navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route){
            MainPage(navController, viewModel, user)
        }


        composable(Screen.MapPage.route){
            MapPage(navController, viewModel, "Карта контейнеров для утилизации отходов")
        }

        composable(Screen.PurchasesPage.route){
            PurchasesPage(navController, viewModel, purchaserViewModel)
        }

        composable(Screen.ProfilePage.route){
            ProfilePage(navController, viewModel, user)
        }

        composable(Screen.CameraPage.route){
            CameraPage(navController, viewModel)
        }

        composable(Screen.VerifyPage.route){
            VerifyAccessScreen(navController, viewModel, purchaserViewModel)
        }

        composable(Screen.Slide1Tip.route){
            EcoTipsPage(
                tip = "Используйте \n меньше пластика",
                description = "Переходите на многоразовые сумки, бутылки и контейнеры, чтобы сократить пластиковые отходы.",
                imageRes = R.drawable.slide1
            )
        }

        composable(Screen.Slide2Tip.route){
            EcoTipsPage(
                tip = "Экономьте воду",
                description = "Выключайте воду, пока чистите зубы, и принимайте короткие душевые сеансы.",
                imageRes = R.drawable.slide1
            )
        }

        composable(Screen.Slide3Tip.route){
            EcoTipsPage(
                tip = "Сажайте деревья",
                description = "Посадка деревьев помогает бороться с выбросами углерода и улучшает качество воздуха.",
                imageRes = R.drawable.slide1
            )
        }

    }
}
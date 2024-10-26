package com.code_wizards.ecology.ui.camera

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.code_wizards.ecology.ui.bottonbar.BottomNavItem
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.viewmodels.MainViewModel

@Composable
fun CameraPage(navController: NavController, viewModel: MainViewModel){

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ) { innerPadding ->
        Column (Modifier.padding(innerPadding).fillMaxSize()){
            Text("Привет, это камера, наверное...")

        }
    }

}
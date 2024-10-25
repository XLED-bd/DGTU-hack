package com.code_wizards.ecology.ui.mainpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.mainpage.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.theme.EcologyTheme


@Composable
fun MainPage(navController: NavController, idUser: Int) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController) }

    ) { innerPadding ->
        Column (Modifier.padding(innerPadding).fillMaxSize()){
            Text("Привет, это главная стриница. Твой айди ${idUser}")

        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    val navController = rememberNavController()

    EcologyTheme {
        MainPage(navController, 1)
    }
}
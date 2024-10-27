package com.code_wizards.ecology.ui.mainpage

import android.annotation.SuppressLint
import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.R
import com.code_wizards.ecology.models.Achievement
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.theme.EcologyTheme
import com.code_wizards.ecology.viewmodels.MainViewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun MainPage(navController: NavController, viewModel: MainViewModel, user: User) {

    val indicate by mutableStateOf(user.carbonFootprint)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ) { innerPadding ->
        Column (Modifier.padding(innerPadding).fillMaxSize()){
            Text("  Здравствуйте, ${user.first_name}", fontSize = 25.sp )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.padding(30.dp)//.fillMaxHeight(0.2f)
                    .fillMaxWidth()
                    .height(380.dp)
                    .background(color = Color(0xFFFFFFFF))
            ){

                Text("  Ваш недельный углеродный след:",
                    Modifier.align(alignment = Alignment.TopStart))

                Image(painter = painterResource(R.drawable.mainindicator),
                    contentDescription = "mainIndicator",
                    modifier = Modifier.align(Alignment.Center).fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Text(text = indicate.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 35.sp)

            }
            Spacer(modifier = Modifier.height(8.dp))

            Slider(navController)

        }
    }

}



@Preview(showBackground = true)
@Composable
fun MainPreview() {

    val navController = rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()
    val user = User(
        1, "Иван", "Иванов", "user1@example.com", "79001234567", "150",
        listOf(
            Achievement("Eco Saver", "Reduced 100kg of carbon"),
            Achievement("Green Thumb", "Planted 10 trees"),
            Achievement("Plastic Warrior", "Avoided 50 plastic bags")
        ),
        emptyList()
    )


    EcologyTheme {
        MainPage(navController, viewModel, user)
    }
}
package com.code_wizards.ecology.ui.profilepage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.code_wizards.ecology.models.Achievement
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.viewmodels.MainViewModel
import com.code_wizards.ecology.viewmodels.ProfileViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfilePage(navController: NavController, viewModel: MainViewModel, user: User){

    val achievements = user.achievements

    val userName = user.first_name
    val userSurname = user.second_name
    val carbonFootprint = user.carbonFootprint

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ){ innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE0F7FA), Color(0xFF80DEEA))
                    )
                )
                .padding(16.dp)
        ) {
            // Фото профиля и информация
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Photo",
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.LightGray, shape = CircleShape)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "$userName $userSurname",
                        color = Color(0xFF344955),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Всего оставлено углеродного следа: $carbonFootprint кг",
                        color = Color(0xFF344955)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Достижения
            Text(
                text = "Достижения",
                color = Color(0xFF344955),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyRow {
                items(achievements.size) { index ->
                    AchievementCard(achievements[index])
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Друзья
            Text(
                text = "Друзья",
                color = Color(0xFF344955),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
//            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                items(friends.size) { index ->
//                    FriendCard(friend = friends[index])
//                }
//            }
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors( containerColor = Color.White ),
        elevation = CardDefaults.cardElevation( 4.dp),
        modifier = Modifier
            .width(200.dp)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = achievement.title,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF344955)
            )
            Text(
                text = achievement.description,
                color = Color(0xFF607D8B),
                textAlign = TextAlign.Center
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PurchasePreview() {
//    val navController = rememberNavController()
//    val mainViewModel: MainViewModel = hiltViewModel()
//
//    EcologyTheme {
//        ProfilePage(navController, mainViewModel, 1)
//    }
//}
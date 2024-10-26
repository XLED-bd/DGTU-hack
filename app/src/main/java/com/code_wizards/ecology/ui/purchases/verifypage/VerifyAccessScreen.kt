package com.code_wizards.ecology.ui.purchases.verifypage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.code_wizards.ecology.models.AccessCodeState
import com.code_wizards.ecology.models.PurchaserState
import com.code_wizards.ecology.navigation.Screen
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.viewmodels.MainViewModel
import com.code_wizards.ecology.viewmodels.PurchaserViewModel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun VerifyAccessScreen(
    navController: NavController,
    viewModel: MainViewModel,
    purchaserViewModel: PurchaserViewModel
) {
    var verificationCode by remember { mutableStateOf("") }
    val accessCodeState by purchaserViewModel.accessCodeState.collectAsState()
    val purchaserState by purchaserViewModel.purchaserState.collectAsState()

    val purchaser = (purchaserState as PurchaserState.Success).purchaser

    purchaserViewModel.requestAccessCode(purchaser.id)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ){ innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Подтверждение доступа",
                )
                Spacer(modifier = Modifier.width(48.dp)) // For alignment
            }

            Spacer(modifier = Modifier.height(32.dp))

            when (accessCodeState) {
                is AccessCodeState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text("Загрузка", modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is AccessCodeState.CodeSent -> {
                    Text(
                        text = "Код подтверждения отправлен",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = verificationCode,
                        onValueChange = { verificationCode = it },
                        label = { Text("Введите код подтверждения") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            purchaserViewModel.verifyAccessCode(
                                purchaser.id,
                                verificationCode
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Подтвердить")
                    }
                }

                is AccessCodeState.Verified -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Screen.MapPage.route)
                    }
                }

                is AccessCodeState.Error -> {
                    Text(
                        text = (accessCodeState as AccessCodeState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                    Button(
                        onClick = {purchaserViewModel.requestAccessCode(purchaser.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Отправить код повторно")
                    }
                }

                else -> Unit
            }
        }
    }
}
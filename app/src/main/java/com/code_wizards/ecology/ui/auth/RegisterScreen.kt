package com.code_wizards.ecology.ui.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.code_wizards.ecology.navigation.Screen
import com.code_wizards.ecology.viewmodels.AuthViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RegisterScreen(viewModel: AuthViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.username_.value,
            onValueChange = { viewModel.username_.value = it },
            label = { Text("Имя") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.username.value,
            onValueChange = { viewModel.username.value = it },
            label = { Text("Почта или телефон") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.confirmPassword.value,
            onValueChange = { viewModel.confirmPassword.value = it },
            label = { Text("Потвердить пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.register() }) {
            Text("Зарегистрироваться")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("У вас есть аккаунт? Войдите")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.errorMessage.value.isNotEmpty()) {
            Text(text = viewModel.errorMessage.value, color = Color.Red)
        }
        if (viewModel.errorMessage.value == "Login successful"){
            navController.navigate("login"){
                launchSingleTop = true
                popUpTo("login") {
                    saveState = true
                }
            }
        }
    }
}

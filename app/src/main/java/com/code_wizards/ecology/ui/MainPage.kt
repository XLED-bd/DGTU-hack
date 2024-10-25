package com.code_wizards.ecology.ui

import android.widget.VideoView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController


@Composable
fun MainPage(modifier: Modifier, navController: NavController, /* viewModel: ViewModel */) {

    Box (modifier.fillMaxSize()){
        Text("Привет, это главная стриница")
    }

}
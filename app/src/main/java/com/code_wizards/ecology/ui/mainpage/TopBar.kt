package com.code_wizards.ecology.ui.mainpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(string: String = ""){
    val color = Color(0xFFEEEEEE)

    Box(
        modifier = Modifier.padding(top = 25.dp).height(50.dp).fillMaxWidth()
    ){
        Text(text = string, Modifier.align(Alignment.Center), fontSize = 14.sp)


        Button(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 5.dp).width(40.dp),
            colors = ButtonColors(color, color, color, color),
            onClick = { }) {
        }


    }
}
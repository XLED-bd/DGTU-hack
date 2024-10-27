package com.code_wizards.ecology.ui.mainpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.code_wizards.ecology.R
import com.code_wizards.ecology.logic.calculateScale
import com.code_wizards.ecology.navigation.Screen

@Composable
fun Slider(navController: NavController){
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            val scale = calculateScale(listState, 0)
            Card(
                Modifier
                    .width(343.dp)
                    .height(182.dp)
                    .padding(8.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }, RoundedCornerShape(16.dp), CardDefaults.cardColors(
                    containerColor = Color(0xFFD7FFD4),
                ), CardDefaults.cardElevation(8.dp)
            ) {
                Box{
                    Image(
                        painter = painterResource(R.drawable.slide1),
                        contentDescription = "Slide1",
                        modifier = Modifier.align(Alignment.CenterEnd)
                            .graphicsLayer {
                                scaleX = 0.6f
                                scaleY = 0.6f
                            }
                            .padding(end = 15.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Используйте меньше пластика",
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "descp1",
//                            color = Color(0xFF000000)
//                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { navController.navigate(Screen.Slide1Tip.route) }) {
                            Text("Показать")
                        }
                    }
                }
            }
        }

        item { val scale = calculateScale(listState, 1)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0CA201),
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .width(343.dp)
                    .height(182.dp)
                    .padding(8.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            ) {
                Box{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Экономьте воду",
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "descp2",
//                            color = Color(0xFF000000)
//                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { navController.navigate(Screen.Slide2Tip.route) }) {
                            Text("Показать")
                        }
                    }

                    Image(
                        painter = painterResource(R.drawable.slide1),
                        contentDescription = "Slide2",
                        modifier = Modifier.align(Alignment.CenterEnd)
                            .graphicsLayer {
                                scaleX = 1f
                                scaleY = 1f
                            }
                            .padding(end = 15.dp)
                    )
                }
            }
        }

        item { val scale = calculateScale(listState, 2)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFDB24),
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .width(343.dp)
                    .height(182.dp)
                    .padding(8.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            ) {
                Box{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Сажайте деревья",
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "descp3",
//                            color = Color(0xFF000000)
//                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = { navController.navigate(Screen.Slide3Tip.route) }) {
                            Text("Показать")
                        }
                    }

                    Image(
                        painter = painterResource(R.drawable.slide1),
                        contentDescription = "Slide3",
                        modifier = Modifier.align(Alignment.CenterEnd)
                            .graphicsLayer {
                                scaleX = 1f
                                scaleY = 1f
                            }
                            .padding(end = 15.dp)
                    )
                }
            }
        }
    }
}
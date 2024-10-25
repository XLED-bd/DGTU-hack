package com.code_wizards.ecology.ui.purchases

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.models.Purchase
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.ui.theme.EcologyTheme
import com.code_wizards.ecology.viewmodels.MainViewModel
import com.code_wizards.ecology.viewmodels.PurchasesViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PurchasesPage(navController: NavController, viewModel: MainViewModel, id_user: Int){

    val purchasesViewModel: PurchasesViewModel = hiltViewModel()

    purchasesViewModel.loadPurchases(id_user)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFFEDF5E1), Color(0xFFA8E6CF)))).padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Purchase History",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF344955),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(purchasesViewModel.purchases.value.size) { index ->
                    PurchaseItemCard(purchase = purchasesViewModel.purchases.value[index])
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PurchasePreview() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()

    EcologyTheme {
        PurchasesPage(navController, mainViewModel, 1)
    }
}
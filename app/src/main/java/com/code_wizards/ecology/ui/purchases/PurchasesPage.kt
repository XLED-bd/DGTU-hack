package com.code_wizards.ecology.ui.purchases

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.models.AccessCodeState
import com.code_wizards.ecology.models.PurchaserState
import com.code_wizards.ecology.models.ReceiptsState
import com.code_wizards.ecology.models.User
import com.code_wizards.ecology.navigation.Screen
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.ui.theme.EcologyTheme
import com.code_wizards.ecology.viewmodels.MainViewModel
import com.code_wizards.ecology.viewmodels.PurchaserViewModel
import com.code_wizards.ecology.viewmodels.ReceiptsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PurchasesPage(navController: NavController, viewModel: MainViewModel,
                  purchaserViewModel: PurchaserViewModel
){
    val purchaserState by purchaserViewModel.purchaserState.collectAsState()
    val purchaser = (purchaserState as PurchaserState.Success).purchaser

    val receiptsViewModel : ReceiptsViewModel = hiltViewModel()

    receiptsViewModel.loadReceipts(purchaser.id)

    val receiptsState by receiptsViewModel.receiptsState.collectAsState()

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

            when (receiptsState) {
                is ReceiptsState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text("Загрузка", modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is ReceiptsState.Success -> {

                    val receipts = (receiptsState as ReceiptsState.Success).receipts

                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(receipts.size) { index ->
                            PurchaseItemCard(receipts[index])
                        }
                    }
                }

                is ReceiptsState.Error -> {
                    Text(
                        text = (receiptsState as ReceiptsState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                else -> Unit
            }
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
//        PurchasesPage(navController, mainViewModel)
//    }
//}
package com.code_wizards.ecology.ui.mappage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.ui.mainpage.MainPage
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.ui.mainpage.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.theme.EcologyTheme
import com.code_wizards.ecology.viewmodels.MainViewModel
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@Composable
fun MapPage(navController: NavController, viewModel: MainViewModel){

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).padding(20.dp)
        ) {
            val context = LocalContext.current
            val mapView = remember {
                MapView(context).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    controller.setZoom(15.0)
                    controller.setCenter(GeoPoint(55.7558, 37.6173)) // Москва
                }
            }

            DisposableEffect(Unit) {
                onDispose {
                    mapView.onDetach()
                }
            }

            AndroidView(
                factory = { mapView },
                modifier = Modifier.fillMaxSize()
            ) { map ->
                map.onResume()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MapPreview() {

    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    EcologyTheme {
        MapPage(navController, viewModel)
    }
}
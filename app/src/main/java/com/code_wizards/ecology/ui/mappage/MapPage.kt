package com.code_wizards.ecology.ui.mappage

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.code_wizards.ecology.R
import com.code_wizards.ecology.models.MarkerData
import com.code_wizards.ecology.ui.mainpage.MainPage
import com.code_wizards.ecology.ui.mainpage.TopBar
import com.code_wizards.ecology.ui.bottonbar.BottomNavigationBar
import com.code_wizards.ecology.ui.theme.EcologyTheme
import com.code_wizards.ecology.viewmodels.MainViewModel
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

@Composable
fun MapPage(navController: NavController, viewModel: MainViewModel, string: String){

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(string) },
        bottomBar = {  BottomNavigationBar(navController, viewModel) }

    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding).padding(20.dp)
        ) {

            val markers = listOf(
                MarkerData(
                    position = GeoPoint(47.22316717079965, 39.71526582146469),
                    title = "Макулатура",
                    description = "Для сдачи макулаторв",
                    color = R.color.blue
                ),
                MarkerData(
                    position = GeoPoint(47.21486585824713, 39.704818769230315),
                    title = "Безвозвратные отходы",
                    description = "Отходы, которые утилизируются безвозвратно",
                    color = R.color.red
                ),
                MarkerData(
                    position = GeoPoint(47.24093019156015, 39.70926055936265),
                    title = "Химические отходы",
                    description = "Для химических отходов",
                    color = R.color.brow
                )
            )

            val context = LocalContext.current
            val mapView = remember {
                MapView(context).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    controller.setZoom(15.0)
                    setMultiTouchControls(true)
                    controller.setCenter(GeoPoint(47.2291,39.7152 )) // Ростов
                }
            }

            fun createCustomMarker(markerData: MarkerData): Marker {
                return Marker(mapView).apply {
                    position = markerData.position
                    title = markerData.title
                    snippet = markerData.description

                    infoWindow = CustomInfoWindow(mapView) { marker ->
                        // Находим соответствующие данные маркера и вызываем обработчик
                        markers.find { it.position == marker.position }?.let {
                            (it)
                        }
                    }

                    // Настройка внешнего вида маркера
                    icon = createMarkerIcon(context, r=markerData.color)

                    // Настройка поведения при клике
                    setOnMarkerClickListener { marker, mapView ->
                        InfoWindow.closeAllInfoWindowsOn(mapView)
                        // Показываем инфо-окно текущего маркера
                        marker.showInfoWindow()
                        // Центрируем карту на маркере
                        mapView.controller.animateTo(marker.position)
                        true
                    }

                }
            }


            DisposableEffect(markers) {
                // Очищаем старые маркеры
                mapView.overlays.clear()

                // Добавляем новые маркеры
                markers.forEach { markerData ->
                    val marker = createCustomMarker(markerData)
                    mapView.overlays.add(marker)
                }

                // Обновляем карту
                mapView.invalidate()

                onDispose {
                    mapView.onDetach()
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

fun createMarkerIcon(context: Context, r: Int): Drawable? {
    return ContextCompat.getDrawable(context, R.drawable.baseline_place_24)?.apply {
        setTint(context.getColor(r))
    }
}



@Preview(showBackground = true)
@Composable
fun MapPreview() {

    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    EcologyTheme {
        MapPage(navController, viewModel, "Карта контейнеров для утилизации отходов")
    }
}
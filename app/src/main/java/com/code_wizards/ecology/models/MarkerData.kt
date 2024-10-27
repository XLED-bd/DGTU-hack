package com.code_wizards.ecology.models

import androidx.compose.ui.graphics.Color
import org.osmdroid.util.GeoPoint

data class MarkerData(
    val position: GeoPoint,
    val title: String,
    val description: String,
    val color: Int
)
package com.code_wizards.ecology.ui.mappage

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.code_wizards.ecology.R
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class CustomInfoWindow(
    private val mapView: MapView,
    private val onActionButtonClick: ((Marker) -> Unit)? = null
) : InfoWindow(R.layout.marker_info_window, mapView) {

    private var currentMarker: Marker? = null

    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)

        if (item !is Marker) return
        currentMarker = item

        // Получаем ссылки на views
        val titleText = mView.findViewById<TextView>(R.id.title_text)
        val descriptionText = mView.findViewById<TextView>(R.id.description_text)
        val actionButton = mView.findViewById<Button>(R.id.action_button)

        // Устанавливаем данные
        titleText.text = item.title
        descriptionText.text = item.snippet

        // Настраиваем кнопку действия
        if (onActionButtonClick != null) {
            actionButton.visibility = View.VISIBLE
            actionButton.setOnClickListener {
                onActionButtonClick.invoke(item)
                close() // Закрываем окно после нажатия
            }
        } else {
            actionButton.visibility = View.GONE
        }
    }

    override fun onClose() {
        currentMarker = null
    }
}

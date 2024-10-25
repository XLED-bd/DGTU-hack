package com.code_wizards.ecology

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.code_wizards.ecology.navigation.AuthNavHost
import com.code_wizards.ecology.ui.theme.EcologyTheme
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Configuration.getInstance().load(
            applicationContext,
            getSharedPreferences("osm_prefs", Context.MODE_PRIVATE)
        )

        setContent {
            EcologyTheme {
                AuthNavHost()
            }
        }
    }
}

package com.example.practicaprehalloween

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.practicaprehalloween.presentation.screens.AppNavigation
import com.example.practicaprehalloween.theme.PracticaPreHalloweenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaPreHalloweenTheme {
                //Esto nos sirve para aplicar el tema principal de la app
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Aqui es donde llamo al sistema de navegacion que estoy construyendo
                    AppNavigation()
                }
            }
        }
    }
}


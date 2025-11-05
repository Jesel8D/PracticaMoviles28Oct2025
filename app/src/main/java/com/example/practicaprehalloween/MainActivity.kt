package com.example.practicaprehalloween

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.practicaprehalloween.presentation.screens.AppNavigation
import com.example.practicaprehalloween.presentation.screens.theme.ThemeViewModel
import com.example.practicaprehalloween.theme.PracticaPreHalloweenTheme

class MainActivity : ComponentActivity() {
    //Obtenemos una instancia del ThemeViewModel a nivel de esta actividad
    private val themeViewModel by viewModels<ThemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Nos suscribimos al estado del tema
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            PracticaPreHalloweenTheme(
                darkTheme = isDarkMode
            ) {


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


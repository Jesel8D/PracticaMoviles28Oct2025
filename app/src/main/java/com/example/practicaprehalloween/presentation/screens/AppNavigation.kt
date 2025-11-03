package com.example.practicaprehalloween.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicaprehalloween.presentation.screens.dashboard.DashboardScreen
import org.w3c.dom.Text

object AppRoutes {
    const val DASHBOARD = "dashboard"
    const val FORM = "form"
    const val THEME = "theme"
}

@Composable
fun AppNavigation() {
    //Aca creamos el NavController que usaremos
    val navController = rememberNavController()

    //Creamos el NavHost que nos sirve como un contenedor
    NavHost(
        navController = navController, startDestination = AppRoutes.DASHBOARD
    ) {
        //Aca definimos cada pantalla, o sea los destinos a las cuales nos vamos a mover
        composable(AppRoutes.DASHBOARD) {
            //Aqui pasamos las acciones de navegacion
            DashboardScreen(
                onNavigateToForm = { navController.navigate(AppRoutes.FORM) },
                onNavigateToTheme = { navController.navigate(AppRoutes.THEME) })
        }
        //Esta seccion va a ser para el FormScreen
        composable(AppRoutes.FORM) {
            PlaceholderScreen("Formulario de FormScreen")
        }

        //Seccion para el ThemeScreen
        composable(AppRoutes.THEME) {
            PlaceholderScreen("Formulario de ThemeScreen")
        }
    }
}


//Este es Composable temporal para cuando cambiemos de pantallas, tipo una pantalla de carga
@Composable
fun PlaceholderScreen(text: String) {
    //Elemento tipo caja que usaremos
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text) //Mostraremos el texto que pasemos como parametro cuando lo invoquemos
    }

}
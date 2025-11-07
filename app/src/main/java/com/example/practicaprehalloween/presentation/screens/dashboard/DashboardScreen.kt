package com.example.practicaprehalloween.presentation.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    //Esto es una funcion lambda que nos va a servir para cuando naveguemos hacia la pantalla de formulario
    onNavigateToForm: () -> Unit,
    //Esto es una funcion lambda que nos va a servir para cuando naveguemos hacia la pantalla de temaas
    onNavigateToTheme: () -> Unit
) {
    Scaffold(topBar = {
        //Seccion tipo "cabecera" que aparece en nuestra vista
        TopAppBar(
            colors = topAppBarColors(
                //Color de contenedor primario
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                //Contenedor de titulo primario
                titleContentColor = MaterialTheme.colorScheme.primary
            ), title = {
                Text("Dashboard")
            })
    }, bottomBar = {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.inverseSurface,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "App"
            )
        }

    }, floatingActionButton = {
        //Ahora el FAB (Floating Action Button) tendria el proposito de navegar hacia la pantalla de formulario
        //Entonces llamamos a la funcion lambda que estamos pasando como parametro
        FloatingActionButton(onClick = onNavigateToForm) {
            Icon(Icons.Default.Add, contentDescription = "Añadir datos")
        }
    }) {
        //Usamos inner padding para que el contenido no se solape con las barras
            innerPading ->
        //Columna principal
        Column(
            modifier = Modifier
                .padding(innerPading)
                .fillMaxSize() //Aqui hacemos que se use todo el espacio disponible
                .padding(16.dp), // Padding  interno adicional para que no este pegado a los bordes
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bienvendo al panel",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            //Este boton nos va a ayudar a navegar a la pagina del formulario
            Button(
                onClick = onNavigateToForm,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ir al formulario")
                Icon(
                    Icons.Default.ArrowForward,
                    null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            //Este boton nos va a ayudar para navegar a la pantalla de tema
            Button(
                onClick = onNavigateToTheme,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Configurar tema")
                Icon(
                    Icons.Default.ArrowForward,
                    null,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
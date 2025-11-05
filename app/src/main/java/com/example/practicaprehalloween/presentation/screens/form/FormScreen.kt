package com.example.practicaprehalloween.presentation.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import com.example.practicaprehalloween.presentation.screens.form.FormViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    //Ahora con Android se encarga de pasarnos el "Application" context
    viewModel: FormViewModel = viewModel(),
    onNavigateBack: () -> Unit // Esto es para poder volver
) {
    //Este estado si es local de la UI (esto seria lo que el usuario escribe)

    //Aca nos suscribimos, asi como en Angular
    val savedData by viewModel.savedData.collectAsState()

    var textValue by remember { mutableStateOf("") }
    Scaffold(
        //Esta es la puerta para poder regresar a la anterior pagina
        topBar = {
            TopAppBar(
                title = { Text("Formulario") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Volver al dashboard"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) //Aca estamos usando el padding del scaffold
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Escribe aqui tus datos") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    //Aca va la logica de la vista, aca notificamos al ViewModel
                    viewModel.saveUserData(textValue)
                }, modifier = Modifier.padding(top = 16.dp)
            ) { Text("Guardar datos") }
            Spacer(modifier = Modifier.height(32.dp))

            //En esta seccion vamos a mostrar los datos guardados
            Text(
                text = "Dato guardado actualmente:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = savedData.ifEmpty { "Aun no has guardado nada" },
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

}
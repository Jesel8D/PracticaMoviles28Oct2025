package com.example.practicaprehalloween.presentation.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicaprehalloween.presentation.screens.form.FormViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    //Ahora la pantalla recibe el ViewModel mas no lo crea
    viewModel: FormViewModel,
    onNavigateBack: () -> Unit // Esto es para poder volver
) {
    //Este estado si es local de la UI (esto seria lo que el usuario escribe)
    var textValue by remember { mutableStateOf("") }
    Scaffold { padding ->
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
                },
                modifier = Modifier.padding(top = 16.dp)
            ) { Text("Guardar datos") }
        }
    }

}
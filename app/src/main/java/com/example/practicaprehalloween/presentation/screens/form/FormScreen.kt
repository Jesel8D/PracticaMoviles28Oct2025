package com.example.practicaprehalloween.presentation.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
// 1. Importamos LazyColumn y items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
// 2. Importamos la Entity para saber qué tipo de datos mostrar
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    viewModel: FormViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    // 3. 'savedData' ahora es un StateFlow<List<FormEntity>>
    val savedData by viewModel.savedData.collectAsState()
    var textValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario Room DB") }, // Título actualizado
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al Dashboard"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // --- Esta parte (Input y Botón) no cambia ---
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Escribe aqui tus datos") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.saveUserData(textValue)
                    textValue = "" // Limpiamos el campo
                }, modifier = Modifier.padding(top = 16.dp)
            ) { Text("Guardar en Room") } // Texto del botón actualizado

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Historial de datos guardados:",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {
                // 5. 'savedData' es la List<FormEntity>
                items(savedData) { entity ->
                    Text(
                        text = "ID ${entity.id}: ${entity.data}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
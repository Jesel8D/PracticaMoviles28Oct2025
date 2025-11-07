package com.example.practicaprehalloween.presentation.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    viewModel: FormViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val savedData by viewModel.savedData.collectAsState()

    // Tres estado para los textfields! ---
    var name by remember { mutableStateOf("") }
    var powerRanger by remember { mutableStateOf("") }
    var cartoon by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario de Favoritos") }, // Título actualizado
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

            // Tres texfields
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Ingresa tu nombre:") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = powerRanger,
                onValueChange = { powerRanger = it },
                label = { Text("Power Ranger favorito:") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = cartoon,
                onValueChange = { cartoon = it },
                label = { Text("Caricatura favorita:") },
                modifier = Modifier.fillMaxWidth()
            )

            //BOTÓN DE GUARDAR
            Button(
                onClick = {
                    // Llamamos al ViewModel con los tres valores
                    viewModel.saveUserData(name, powerRanger, cartoon)
                    // Limpiamos los campos
                    name = ""
                    powerRanger = ""
                    cartoon = ""
                }, modifier = Modifier.padding(top = 16.dp)
            ) { Text("Guardar en Room") }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Historial de datos guardados:",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                items(savedData) { entity ->
                    // Mostramos los nuevos datos en una tarjeta
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "ID ${entity.id}: ${entity.name}",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Ranger: ${entity.powerRanger}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Caricatura: ${entity.cartoon}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
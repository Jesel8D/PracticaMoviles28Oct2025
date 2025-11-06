package com.example.practicaprehalloween.presentation.screens.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
// 1. Importamos todas nuestras clases de Room
import com.example.practicaprehalloween.data.datasource.local.db.AppDatabase
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.data.repository.FormRepositoryImpl
import com.example.practicaprehalloween.domain.repository.FormRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FormRepository

    // 2. ¡El estado ahora es una LISTA de Entities!
    val savedData: StateFlow<List<FormEntity>>

    init {
        // --- ¡La nueva cadena de construcción de Room! ---
        val context = application.applicationContext

        // 3. Obtenemos la instancia de la Base de Datos
        val database = AppDatabase.getInstance(context)

        // 4. De la base de datos, obtenemos el DAO
        val formDao = database.formDao()

        // 5. Creamos el DataSource y le inyectamos el DAO
        val dataSource = FormLocalDataSource(formDao)

        // 6. Creamos el Repositorio (¡este paso es igual que antes!)
        repository = FormRepositoryImpl(dataSource)
        // ------------------------------------------------

        // 7. "Escuchamos" al repositorio
        savedData = repository.getFormData()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList() // El valor inicial ahora es una lista vacía
            )
    }

    // --- ¡¡MIRA ESTO!! ---
    // ¡La función 'save' NO CAMBIA NADA!
    // Gracias a la arquitectura, la UI sigue llamando a la misma
    // función, y la lógica interna se encarga del resto.
    fun saveUserData(data: String) {
        viewModelScope.launch {
            if (data.isNotBlank()) {
                repository.saveForm(data)
            }
        }
    }
}
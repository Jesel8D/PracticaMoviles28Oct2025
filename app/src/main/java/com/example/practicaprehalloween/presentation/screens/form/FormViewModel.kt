package com.example.practicaprehalloween.presentation.screens.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
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
    val savedData: StateFlow<List<FormEntity>>

    init {
        val context = application.applicationContext
        val database = AppDatabase.getInstance(context)
        val formDao = database.formDao()
        val dataSource = FormLocalDataSource(formDao)
        repository = FormRepositoryImpl(dataSource)

        savedData = repository.getFormData()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    // La función ahora acepta los tres strings
    fun saveUserData(name: String, powerRanger: String, cartoon: String) {
        viewModelScope.launch {
            // Hacemos una validacion simple
            if (name.isNotBlank() && powerRanger.isNotBlank() && cartoon.isNotBlank()) {
                // Y llamamos al repositorio con los nuevos parametros
                repository.saveForm(name, powerRanger, cartoon)
            }
        }
    }
}
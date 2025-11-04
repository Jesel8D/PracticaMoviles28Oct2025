package com.example.practicaprehalloween.presentation.screens.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.data.repository.FormRepositoryImpl
import com.example.practicaprehalloween.domain.repository.FormRepository
import com.example.practicaprehalloween.domain.usecase.form.SaveFormDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FormViewModel(
    application: Application
) : AndroidViewModel(application)//Aca heredamos del ViewModel
{
    //Creamos nuestras propias dependencias
    private val repository: FormRepository

    init {
        //Obtenmemos el context de forma segura
        val context = application.applicationContext
        //Creamos la cadena de Datasource a Repository
        val dataSource = FormLocalDataSource(application.applicationContext)
        repository = FormRepositoryImpl(dataSource)
    }

    //Aca hacemos uso de las Coroutines
    fun saveUserData(data: String) {
        //Lanzamos la coroutine
        viewModelScope.launch {
            try {
                //Llamamos directamente al repositorio
                repository.saveForm(data)
                //Si me da tiempo agrego un StateFlow
            } catch (e: Exception) {
                println("Error al guardar:${e.message}")
            }
        }
    }

    //Funcion por si ocupamos leer los datos despues
    fun getSavedData(): Flow<String> {
        return repository.getFormData()
    }


}
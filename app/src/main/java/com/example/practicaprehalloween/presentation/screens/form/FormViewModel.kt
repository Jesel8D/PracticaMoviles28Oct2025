package com.example.practicaprehalloween.presentation.screens.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.data.repository.FormRepositoryImpl
import com.example.practicaprehalloween.domain.repository.FormRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class FormViewModel(
    application: Application
) : AndroidViewModel(application)//Aca heredamos del ViewModel
{
    //Creamos nuestras propias dependencias
    private val repository: FormRepository


    //En esta parte conectaremos el flujo de lectura
    //Lo haremos creando un estado publico que la UI podra observar
    //Esto va a ser un StateFlow que es un tipo de Flow ideal para la UI
    val savedData: StateFlow<String>

    init {
        //Obtenmemos el context de forma segura
        val context = application.applicationContext
        //Creamos la cadena de Datasource a Repository
        val dataSource = FormLocalDataSource(application.applicationContext)
        repository = FormRepositoryImpl(dataSource)
        //Aca estamos diciendole al archivo ViewModel que escuche al repositorio y convertir el Flow 'frio' a un 'caliente'
        savedData = repository.getFormData().stateIn(
            scope = viewModelScope, //Aca lo estamos 'anclando' a la vida del ViewModel
            started = SharingStarted.WhileSubscribed(5000), //Lo va a empezar a escuchar cuando la UI sea visible
            initialValue = "" //Valor inicial mientras se carga
        )
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
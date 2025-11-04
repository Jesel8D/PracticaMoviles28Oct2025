package com.example.practicaprehalloween.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Procedemos a crear una instancia de DataStore para toda nuestra app
private val Context.dataStore by preferencesDataStore("form_settings")

class FormLocalDataSource(private val context: Context) {
    //Creamos la "llave" para guardar nuestro dato
    companion object {
        val FORM_DATA_KEY = stringPreferencesKey("form_data")
    }

    //En esta parte implementaremos las Coroutines
    //Esta funcion es asincrona
    suspend fun saveForm(data: String) {
        context.dataStore.edit { settings ->
            settings[FORM_DATA_KEY] = data
        }
    }

    //Esta funcion es en caso de que despues quiera leer los datos
    fun getFormData(): Flow<String> {
        return context.dataStore.data.map { settings ->
            settings[FORM_DATA_KEY]
                ?: "" //Aca devolvemos el dato o en caso de que no exista (operador elvis) retornamos ""
        }
    }
}
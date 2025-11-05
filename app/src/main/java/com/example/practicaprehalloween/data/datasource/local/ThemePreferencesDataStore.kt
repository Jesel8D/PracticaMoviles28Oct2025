package com.example.practicaprehalloween.data.datasource.local

import android.content.Context
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//Hacemos una instancia de DataStore separada para las preferencias del tema
private val Context.dataStore by preferencesDataStore(name = "theme_settings")

class ThemePreferencesDataStore(
    private val context: Context
) {
    //Aca creamos la llave para guardar nuestro booleano
    companion object {
        val IS_DARK_MODE_KEY = booleanPreferencesKey("is_dark_mode")
    }

    //Coroutine
    //Esta funcion 'suspend' guarda la preferencia
    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { settings ->
            settings[IS_DARK_MODE_KEY] = isDark
        }
    }

    //Esta funcion devuelve un 'Flow' que va a notificar cada vez que el tema cambie
    fun getTheme(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[IS_DARK_MODE_KEY] ?: false //Por defecto retornamos false 'light mode'
        }

    }
}
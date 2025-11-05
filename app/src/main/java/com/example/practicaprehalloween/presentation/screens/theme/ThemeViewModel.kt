package com.example.practicaprehalloween.presentation.screens.theme

import android.app.Application
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaprehalloween.data.datasource.local.ThemePreferencesDataStore
import com.example.practicaprehalloween.data.repository.ThemeRepositoryImpl
import com.example.practicaprehalloween.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    //Creamos la cadena de depdencias al igual que lo hicimos en FormViewModel
    private val repository: ThemeRepository

    val isDarkMode: StateFlow<Boolean>

    init {
        val dataSource = ThemePreferencesDataStore(application.applicationContext)
        repository = ThemeRepositoryImpl(dataSource)

        //Aca escuchamos al repositorio y lo convertimos en un StateFlow
        isDarkMode = repository.getTheme().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false //Light mode sera nuestro tema por defecto
        )
    }

    //Esta funcion es par que la UI llame y guarde la preferencia
    fun saveTheme(isDark: Boolean) {
        viewModelScope.launch {
            repository.saveTheme(isDark)
        }
    }

}
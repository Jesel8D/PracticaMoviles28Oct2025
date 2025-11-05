package com.example.practicaprehalloween.domain.repository

import androidx.compose.ui.tooling.preview.PreviewLightDark
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    //Funcion suspendible para guardar preferencia
    suspend fun saveTheme(isDark: Boolean)

    //Funcion que devuelve un Flow para leer

    fun getTheme(): Flow<Boolean>

}
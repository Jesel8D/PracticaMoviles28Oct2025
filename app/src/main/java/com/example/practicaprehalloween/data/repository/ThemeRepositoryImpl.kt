package com.example.practicaprehalloween.data.repository

import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.data.datasource.local.ThemePreferencesDataStore
import com.example.practicaprehalloween.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeRepositoryImpl(
    private val localDataSource: ThemePreferencesDataStore
) : ThemeRepository {
    override suspend fun saveTheme(isDark: Boolean) {
        localDataSource.saveTheme(isDark)
    }

    override fun getTheme(): Flow<Boolean> {
        return localDataSource.getTheme()
    }
}
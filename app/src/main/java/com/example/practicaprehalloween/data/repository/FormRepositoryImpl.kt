package com.example.practicaprehalloween.data.repository

import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import com.example.practicaprehalloween.domain.repository.FormRepository
import kotlinx.coroutines.flow.Flow

class FormRepositoryImpl(
    private val localDataSource: FormLocalDataSource
) : FormRepository {

    override suspend fun saveForm(data: String) {
        localDataSource.saveForm(data)
    }

    // 2. Implementamos la nueva firma de la interfaz
    override fun getFormData(): Flow<List<FormEntity>> {
        // Simplemente pasamos la llamada al DataSource
        return localDataSource.getAllFormData()
    }
}
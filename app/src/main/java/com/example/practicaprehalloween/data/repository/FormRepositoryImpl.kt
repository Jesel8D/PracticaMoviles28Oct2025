package com.example.practicaprehalloween.data.repository

import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import com.example.practicaprehalloween.domain.repository.FormRepository
import kotlinx.coroutines.flow.Flow

class FormRepositoryImpl(
    private val localDataSource: FormLocalDataSource
) : FormRepository {

    // Implementamos la nueva firma de la interfaz
    override suspend fun saveForm(name: String, powerRanger: String, cartoon: String) {
        // Y pasamos los parametros al DataSource
        localDataSource.saveForm(name, powerRanger, cartoon)
    }

    override fun getFormData(): Flow<List<FormEntity>> {
        return localDataSource.getAllFormData()
    }
}
package com.example.practicaprehalloween.data.repository

import com.example.practicaprehalloween.data.datasource.local.FormLocalDataSource
import com.example.practicaprehalloween.domain.repository.FormRepository
import kotlinx.coroutines.flow.Flow

class FormRepositoryImpl(
    private val localDataSource: FormLocalDataSource
) : FormRepository {

    //Aca hacemos el uso de Coroutines
    //Solamente vamos a llamar a la funcion suspend de DataSource
    override suspend fun saveForm(data: String) {
        localDataSource.saveForm(data)
    }

    //Esta funcion es en caso de queramos leer
    override fun getFormData(): Flow<String> {
        return localDataSource.getFormData()
    }
}
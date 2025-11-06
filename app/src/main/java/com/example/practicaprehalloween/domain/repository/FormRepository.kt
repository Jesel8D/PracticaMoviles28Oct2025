package com.example.practicaprehalloween.domain.repository

// 1. Importamos la 'Entity' (el modelo de datos de Room)
import com.example.practicaprehalloween.data.datasource.local.db.FormEntity
import kotlinx.coroutines.flow.Flow

interface FormRepository {
    // La función 'save' no cambia su firma
    suspend fun saveForm(data: String)

    // 2. La función 'get' ahora devuelve un Flow de una *Lista* de Entities
    fun getFormData(): Flow<List<FormEntity>>
}